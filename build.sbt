name := """first-play-app"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

resolvers += Resolver.sonatypeRepo("snapshots")

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core" % "0.2.0-SNAPSHOT",
  "io.circe" %% "circe-generic" % "0.2.0-SNAPSHOT",
  "io.circe" %% "circe-jawn" % "0.2.0-SNAPSHOT"
)
