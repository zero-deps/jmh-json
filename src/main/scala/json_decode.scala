package json

import org.openjdk.jmh.annotations._

object JsonDecodeStates {
  @State(Scope.Benchmark)
  class Jsr374StreamState {
    import jakarta.json.Json
    import java.io.StringReader
    val parser = Json.createParser(new StringReader("""{"inline_query":{"id":"123","query":"1+1"}}"""))
  }
  @State(Scope.Benchmark)
  class JavaxState {
    import jdk.nashorn.api.scripting._
    val json = """{"inline_query":{"id":"123","query":"1+1"}}"""
    val engine = new NashornScriptEngineFactory().getScriptEngine("-strict", "--no-java", "--no-syntax-extensions", "--no-typed-arrays", "--no-deprecation-warning")
  }
  @State(Scope.Benchmark)
  class JsoniterState {
    val json = """{"inline_query":{"id":"123","query":"1+1"}}"""
    val jsoniterCodec = {
      import com.github.plokhotnyuk.jsoniter_scala.macros._
      import com.github.plokhotnyuk.jsoniter_scala.core._
      JsonCodecMaker.make[model.UpdateMessage]
    }
  }
}

class JsonDecode {
  import JsonDecodeStates._

  @Benchmark
  def jsr374stream(state: Jsr374StreamState): Unit = {
    import jakarta.json.stream.JsonParser
    import state.parser
    var done = false
    while (parser.hasNext && !done) {
      val event = parser.next
      if (event == JsonParser.Event.KEY_NAME ) {
        if (parser.getString == "inline_query") {
          parser.next
          parser.getObject.getString("query")
          done = true
        }
      }
    }
  }

  @Benchmark
  def nashornDecode(state: JavaxState): Unit = {
    import javax.script._
    state.engine.eval(s"(${state.json}).inline_query").asInstanceOf[Bindings].get("query")
  }

  @Benchmark
  def jsoniterDecode(state: JsoniterState): Unit = {
    import com.github.plokhotnyuk.jsoniter_scala.core._
    readFromArray(state.json.getBytes("utf8"))(state.jsoniterCodec).inline_query.query
  }
}
