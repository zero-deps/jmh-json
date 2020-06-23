import sbt._

object Dependencies {
  val scodec = "org.scodec" %% "scodec-core" % "1.11.7"
  val jsoniter = Seq(
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "2.4.3"
  , "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "2.4.3" % "compile-internal"
  )
  val chill = "com.twitter" %% "chill" % "0.9.5"
  val jackson = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.11.0"
  val argonaut = "io.argonaut" %% "argonaut" % "6.3.0"
}
