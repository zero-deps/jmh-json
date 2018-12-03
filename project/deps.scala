import sbt._

object Dependencies {
  val scodec = "org.scodec" %% "core" % "1.10.4"
  val jsoniter = List(
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-core" % "0.36.9" % Compile, 
    "com.github.plokhotnyuk.jsoniter-scala" %% "jsoniter-scala-macros" % "0.36.9" % Provided
  )
  val chill = "com.twitter" %% "chill" % "0.9.3"
  val pickling = "com.playtech.mws" %% "scala-pickling" % "1.0-2-gb05b7b9"
  val jackson = "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.7"
  val argonaut = "io.argonaut" %% "argonaut" % "6.2.2"
}
