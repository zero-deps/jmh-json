package binary

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.runner.options.Options
import org.openjdk.jmh.runner.options.OptionsBuilder
import org.openjdk.jmh.runner.Runner
import org.openjdk.jmh.runner.RunnerException

final case class Data(key: Array[Byte], bucket: Int, lastModified: Long, vc: List[(String,Long)], value: Array[Byte])
final case class Data_argonaut(key: String, bucket: Int, lastModified: Long, vc: List[(String,Long)], value: String)

object States {
  @State(Scope.Benchmark)
  class JavaState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    val x: Array[Byte] = {
      import java.io._
      val bos = new ByteArrayOutputStream
      val out = new ObjectOutputStream(bos)
      out.writeObject(d)
      out.close()
      bos.toByteArray
    }
  }

  @State(Scope.Benchmark)
  class JsoniterState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.github.plokhotnyuk.jsoniter_scala.macros._
    import com.github.plokhotnyuk.jsoniter_scala.core._
    val c: JsonValueCodec[Data] = JsonCodecMaker.make[Data](CodecMakerConfig())
    val x: Array[Byte] = writeToArray(d)(c)
  }

  @State(Scope.Benchmark)
  class PicklingState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import scala.pickling._,Defaults._,binary._
    val x: Array[Byte] = d.pickle.value
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
    val x: Array[Byte] = c.encode(d.key ~ d.bucket ~ d.lastModified ~ d.vc ~ d.value).toOption.get.toByteArray
  }

  @State(Scope.Benchmark)
  class JacksonState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.fasterxml.jackson.module.scala.DefaultScalaModule
    import com.fasterxml.jackson.databind.ObjectMapper
    val m = new ObjectMapper()
    m.registerModule(DefaultScalaModule)
    val x: Array[Byte] = m.writeValueAsBytes(d)
  }

  @State(Scope.Benchmark)
  class ChillState {
    val d = Data((1 to 50).mkString("").getBytes("UTF-8"), bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1))
    import com.twitter.chill._
    val kryo = KryoPool.withByteArrayOutputStream(10, new ScalaKryoInstantiator)
    val x: Array[Byte] = kryo.toBytesWithoutClass(d)
  }

  @State(Scope.Benchmark)
  class ProtobufState {
    import com.google.protobuf.ByteString
    val d = new data.Data(ByteString.copyFrom((1 to 50).mkString("").getBytes("UTF-8")), bucket=10, lastModified=System.currentTimeMillis, vc=List(data.Vec("169.0.0.1:4400", 2000)), value=ByteString.copyFrom(Array.fill[Byte](10000)(1)))
    val x: Array[Byte] = d.toByteArray
  }

  @State(Scope.Benchmark)
  class ArgonautState {
    import argonaut._, Argonaut._
    val d = Data_argonaut((1 to 50).mkString, bucket=10, lastModified=System.currentTimeMillis, vc=List("169.0.0.1:4400"->2000), value=Array.fill(10000)(1).mkString)
    implicit val c = casecodec5(Data_argonaut.apply, Data_argonaut.unapply)("a", "b", "c", "d", "e")
    val x: Array[Byte] = d.asJson.nospaces.getBytes("UTF-8")
  }
}

class ArgonautEncode {
  import argonaut._, Argonaut._

  @Benchmark
  def bench(state: States.ArgonautState): Unit = {
    import state.c
    (state.d.asJson.nospaces.getBytes("UTF-8"): Array[Byte])
  }
}

class ArgonautDecode {
  import argonaut._, Argonaut._

  @Benchmark
  def bench(state: States.ArgonautState): Unit = {
    import state.c
    (new String(state.x, "UTF-8").decodeOption[Data_argonaut].get: Data_argonaut)
  }
}

class ProtobufEncode {
  @Benchmark
  def bench(state: States.ProtobufState): Unit = {
    state.d.toByteArray
  }
}

class ProtobufDecode {
  @Benchmark
  def bench(state: States.ProtobufState): Unit = {
    (data.Data.parseFrom(state.x): data.Data)
  }
}

class ChillEncode {
  @Benchmark
  def bench(state: States.ChillState): Unit = {
    state.kryo.toBytesWithoutClass(state.d)
  }
}

class ChillDecode {
  @Benchmark
  def bench(state: States.ChillState): Unit = {
    (state.kryo.fromBytes(state.x, classOf[Data]): Data)
  }
}

class JacksonEncode {
  @Benchmark
  def bench(state: States.JacksonState): Unit = {
    state.m.writeValueAsBytes(state.d)
  }
}

class JacksonDecode {
  @Benchmark
  def bench(state: States.JacksonState): Unit = {
    (state.m.readValue(state.x, classOf[Data]): Data)
  }
}

class ScodecEncode {
  import scodec.codecs._

  @Benchmark
  def bench(state: States.ScodecState): Unit = {
    state.c.encode(state.d.key ~ state.d.bucket ~ state.d.lastModified ~ state.d.vc ~ state.d.value).toOption.get.toByteArray
  }
}

class ScodecDecode {
  import scodec.codecs._
  import scodec.bits.BitVector

  @Benchmark
  def bench(state: States.ScodecState): Unit = {
    val y = state.c.decode(BitVector.view(state.x)).toOption.get.value
    Data(y._1._1._1._1, y._1._1._1._2, y._1._1._2, y._1._2, y._2)
  }
}

class PicklingEncode {
  import scala.pickling._,Defaults._,binary._

  @Benchmark
  def bench(state: States.PicklingState): Unit = {
    state.d.pickle.value
  }
}

class PicklingDecode {
  import scala.pickling._,Defaults._,binary._

  @Benchmark
  def bench(state: States.PicklingState): Unit = {
    (state.x.unpickle[Data]: Data)
  }
}

class JsoniterEncode {
  import com.github.plokhotnyuk.jsoniter_scala.core._

  @Benchmark
  def bench(state: States.JsoniterState): Unit = {
    writeToArray(state.d)(state.c)
  }
}

class JsoniterDecode {
  import com.github.plokhotnyuk.jsoniter_scala.core._

  @Benchmark
  def bench(state: States.JsoniterState): Unit = {
    (readFromArray(state.x)(state.c): Data)
  }
}

class JavaEncode {
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

class JavaDecode {
  import java.io._

  @Benchmark
  def bench(state: States.JavaState): Unit = {
    val in = new ObjectInputStream(new ByteArrayInputStream(state.x))
    val obj = in.readObject
    in.close()
    obj.asInstanceOf[Data]
  }  
}
