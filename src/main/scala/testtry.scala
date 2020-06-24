package testtry

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException

object States {
  @State(Scope.Benchmark)
  class One {
    val x: String = ""
    val f: Any => String = _ => ""
  }
}

class TestTry {
  import scala.util.{Try}

  @Benchmark
  def none2(state: States.One): Unit = {
    val x: Either[Throwable, String] =
      Right(state.f(state.x))
  }

  @Benchmark
  def try1(state: States.One): Unit = {
    val x: Either[Throwable, String] =
      Try(state.f(state.x)).toEither
  }

  @Benchmark
  def try2(state: States.One): Unit = {
    val x: Either[Throwable, String] =
      try { Right(state.f(state.x)) }
      catch { case x: Throwable => Left(x) }
  }
}
