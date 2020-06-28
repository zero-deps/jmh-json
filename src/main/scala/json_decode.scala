package json

import org.openjdk.jmh.annotations._

object JsonDecodeStates {
  @State(Scope.Benchmark)
  class JsonpStreamState {
    val json = """{"inline_query":{"id":"123","query":"1+1"}}""".getBytes("utf8")
  }
  @State(Scope.Benchmark)
  class JsonpPointerState {
    import jakarta.json.Json
    val json = """{"inline_query":{"id":"123","query":"1+1"}}""".getBytes("utf8")
    val pointer = Json.createPointer("/inline_query")
  }
  @State(Scope.Benchmark)
  class NashornState {
    import jdk.nashorn.api.scripting._
    val json = """{"inline_query":{"id":"123","query":"1+1"}}""".getBytes("utf8")
    val engine = new NashornScriptEngineFactory().getScriptEngine("-strict", "--no-java", "--no-syntax-extensions", "--no-typed-arrays", "--no-deprecation-warning")
  }
  @State(Scope.Benchmark)
  class JsoniterState {
    import com.github.plokhotnyuk.jsoniter_scala.macros._
    import com.github.plokhotnyuk.jsoniter_scala.core._
    val json = """{"inline_query":{"id":"123","query":"1+1"}}""".getBytes("utf8")
    val jsoniterCodec = JsonCodecMaker.make[model.UpdateMessage]
  }
}

class JsonDecode {
  import JsonDecodeStates._
  import model.InlineQuery

  @Benchmark
  def jsonpStream(state: JsonpStreamState): InlineQuery = {
    import jakarta.json.stream.JsonParser
    import jakarta.json.Json
    import java.io._
    val parser = Json.createParser(new InputStreamReader(new ByteArrayInputStream(state.json)))
    var done = false
    var res: InlineQuery = null
    while (parser.hasNext && !done) {
      val event = parser.next
      if (event == JsonParser.Event.KEY_NAME ) {
        if (parser.getString == "inline_query") {
          parser.next
          val iq = parser.getObject
          res = InlineQuery(iq.getString("id"), iq.getString("query"))
          done = true
        }
      }
    }
    res
  }

  @Benchmark
  def jsonpPointer(state: JsonpPointerState): InlineQuery = {
    import jakarta.json.Json
    import java.io._
    val obj = Json.createReader(new InputStreamReader(new ByteArrayInputStream(state.json))).readObject
    val iq = state.pointer.getValue(obj).asJsonObject
    InlineQuery(iq.getString("id"), iq.getString("query"))
  }

  @Benchmark
  def nashorn(state: NashornState): InlineQuery = {
    import javax.script._
    val x = state.engine.eval(s"JSON.parse(json).inline_query", new SimpleBindings(new java.util.HashMap[String,Any](){{ put("json", new String(state.json)) }})).asInstanceOf[Bindings]
    InlineQuery(x.get("id").toString, x.get("query").toString)
  }

  @Benchmark
  def jsoniter(state: JsoniterState): InlineQuery = {
    import com.github.plokhotnyuk.jsoniter_scala.core._
    readFromArray(state.json)(state.jsoniterCodec).inline_query
  }
}
