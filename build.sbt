name := "restproject"

version := "0.1"
val akkaHttpVersion = "10.1.10"
scalaVersion := "2.13.1"
resolvers += Resolver.bintrayRepo("hseeberger", "maven")
libraryDependencies ++= Seq(
  // DB
  "com.typesafe.slick" %% "slick" % "3.3.2",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.2",
  "org.postgresql" % "postgresql" % "9.4-1206-jdbc42",
  "com.h2database" % "h2" % "1.4.199",
  // AKKA
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.23",
  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion,
  //Mapper
  "io.circe" %% "circe-core" % "0.12.2",
  "io.circe" %% "circe-generic" % "0.12.2",
  "io.circe" %% "circe-parser" % "0.12.2",
  "de.heikoseeberger" %% "akka-http-circe" % "1.29.1",
  "de.heikoseeberger" %% "akka-http-play-json" % "1.29.1",
  "joda-time" % "joda-time" % "2.9.9",
  "org.joda" % "joda-convert" % "1.8.1",
  // TEST
  "org.scalatest" %% "scalatest" % "3.0.8" % Test,
  "com.softwaremill.sttp" %% "core" % "1.7.0" % Test,

)
enablePlugins(JavaAppPackaging)

dockerBaseImage := "anapsix/alpine-java"
dockerExposedPorts ++= Seq(8888)
