import Dependencies._

lazy val root = (project in file(".")).
  settings(
    scalaVersion := "2.12.7",
    version := "0.0.1",
    name := "bench",
    libraryDependencies += scodec,
    libraryDependencies ++= jsoniter,
    libraryDependencies += chill,
    libraryDependencies += pickling,
    libraryDependencies += jackson,
    libraryDependencies += argonaut,
    resolvers += "Sonatype Public" at "https://oss.sonatype.org/content/groups/public/",
    resolvers += Resolver.bintrayRepo("evolutiongaming", "maven"),
    cancelable in Global := true,
  )

enablePlugins(JmhPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)
