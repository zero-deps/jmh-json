package binary

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException

final case class Data(key: Array[Byte], bucket: Int, lastModified: Long, vc: List[(String,Long)], value: Array[Byte])

object States {
  @State(Scope.Benchmark)
  class JavaState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
  }

  @State(Scope.Benchmark)
  class JsoniterState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.github.plokhotnyuk.jsoniter_scala.macros._
    import com.github.plokhotnyuk.jsoniter_scala.core._
    val c: JsonValueCodec[Data] = JsonCodecMaker.make[Data](CodecMakerConfig())
  }

  @State(Scope.Benchmark)
  class PicklingState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
  }

  @State(Scope.Benchmark)
  class ScodecState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import scodec.codecs._
    import scodec.{Attempt, DecodeResult, Err => CErr, Codec, SizeBound}
    import scodec.bits.BitVector
    val abytes = new Codec[Array[Byte]] {
      def sizeBound = SizeBound.unknown
      def encode(a: Array[Byte]) = Attempt.successful(BitVector.view(a))
      def decode(bytes: BitVector) = Attempt.successful(DecodeResult(bytes.toByteArray, BitVector.empty))
      override def toString = "byteArray"
    }
    val c = abytes ~ vint ~ vlong ~ list(utf8 ~ vlong) ~ abytes
  }

  @State(Scope.Benchmark)
  class JacksonState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.fasterxml.jackson.module.scala.DefaultScalaModule
    import com.fasterxml.jackson.databind.ObjectMapper
    val m = new ObjectMapper()
    m.registerModule(DefaultScalaModule)
  }

  @State(Scope.Benchmark)
  class ChillState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.twitter.chill._
    val kryo = KryoPool.withByteArrayOutputStream(10, new KryoInstantiator)
  }

  @State(Scope.Benchmark)
  class ProtobufState {
    import com.google.protobuf.ByteString
    val d = new data.Data(ByteString.copyFrom((1 to 50).mkString("").getBytes("UTF-8")), bucket=10, lastModified=System.currentTimeMillis, vc=List(data.Vec("169.0.0.1:4400", 2000)), value=ByteString.copyFrom(Array.fill[Byte](10000)(1)))
  }
}

class ProtobufEncode1 {
  @Benchmark
  def bench(state: States.ProtobufState): Unit = {
    state.d.toByteArray
  }
}

class ChillEncode1 {
  @Benchmark
  def bench(state: States.ChillState): Unit = {
    state.kryo.toBytesWithClass(state.d)
  }
}

class JacksonEncode1 {
  @Benchmark
  def bench(state: States.JacksonState): Unit = {
    state.m.writeValueAsBytes(state.d)
  }
}

class ScodecEncode1 {
  import scodec.codecs._

  @Benchmark
  def bench(state: States.ScodecState): Unit = {
    /*
    c.encode(d.key ~ d.bucket ~ d.lastModified ~ d.vc ~ d.value).toOption.get.toByteArray.length
    */
    state.c.encode(state.d.key ~ state.d.bucket ~ state.d.lastModified ~ state.d.vc ~ state.d.value).toOption.get.toByteArray
  }
}

class PicklingEncode1 {
  import scala.pickling._,Defaults._,binary._

  @Benchmark
  def bench(state: States.PicklingState): Unit = {
    state.d.pickle.value
  }
}

class JsoniterEncode1 {
  import com.github.plokhotnyuk.jsoniter_scala.core._

  @Benchmark
  def bench(state: States.JsoniterState): Unit = {
    writeToArray(state.d)(state.c)
  }
}

class JavaEncode1 {
  import java.io._

  @Benchmark
  def bench(state: States.JavaState): Unit = {
    val bos = new ByteArrayOutputStream
    val out = new ObjectOutputStream(bos)
    out.writeObject(state.d)
    out.close()
    bos.toByteArray
  }
}
