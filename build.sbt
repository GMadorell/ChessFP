val Http4sVersion = "0.18.21"
val Specs2Version = "4.1.0"
val LogbackVersion = "1.2.3"

resolvers += Resolver.sonatypeRepo("releases")

lazy val root = (project in file("."))
  .settings(
    organization := "com.gmadorell",
    name := "chessfp",
    version := "0.0.1-SNAPSHOT",
    scalaVersion := "2.12.7",
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % LogbackVersion,
      "eu.timepit" %% "refined" % "0.9.3",
      "eu.timepit" %% "refined-scalacheck" % "0.9.3",
      "com.beachape" %% "enumeratum" % "1.5.13",
      "org.specs2" %% "specs2-core" % Specs2Version % "test",
      "org.scalatest" %% "scalatest" % "3.0.5" % "test",
      "com.danielasfregola" %% "random-data-generator" % "2.6" % "test",
      "com.ironcorelabs" %% "cats-scalatest" % "2.3.1" % "test"
    ),
    addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.6"),
    addCompilerPlugin("com.olegpy" %% "better-monadic-for" % "0.2.4")
  )

addCommandAlias("t", "test")
addCommandAlias("to", "testOnly")
addCommandAlias("tq", "testQuick")
addCommandAlias("tsf", "testShowFailed")

addCommandAlias("c", "compile")
addCommandAlias("tc", "test:compile")

addCommandAlias("s", "scalastyle")
addCommandAlias("ts", "test:scalastyle")

addCommandAlias("f", "scalafmt")
addCommandAlias("ft", "scalafmtCheck")
addCommandAlias("tf", "test:scalafmt")
addCommandAlias("tft", "test:scalafmtCheck")
