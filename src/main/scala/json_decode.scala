package json

import org.openjdk.jmh.annotations._

object JsonDecodeStates {
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
  def javaxDecode(state: JavaxState): Unit = {
    import javax.script._
    state.engine.eval(s"(${state.json}).inline_query").asInstanceOf[Bindings].get("query")
  }

  @Benchmark
  def jsoniterDecode(state: JsoniterState): Unit = {
    import com.github.plokhotnyuk.jsoniter_scala.core._
    readFromArray(state.json.getBytes("utf8"))(state.jsoniterCodec).inline_query.query
  }
}
