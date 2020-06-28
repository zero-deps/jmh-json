import Dependencies._

lazy val root = (project in file(".")).
  settings(
    scalaVersion := "2.13.2",
    version := "0.0.1",
    name := "bench",
    libraryDependencies += scodec,
    libraryDependencies ++= jsoniter,
    libraryDependencies += chill,
    libraryDependencies += jackson,
    libraryDependencies += argonaut,
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.1.0" % Test,
    resolvers += "Sonatype Public" at "https://oss.sonatype.org/content/groups/public/",
    resolvers += Resolver.bintrayRepo("evolutiongaming", "maven"),
    cancelable in Global := true,
  )

enablePlugins(JmhPlugin)

PB.targets in Compile := Seq(
  scalapb.gen() -> (sourceManaged in Compile).value
)

Global / onChangedBuildSource := ReloadOnSourceChanges
Global / cancelable := true
Global / fork := true