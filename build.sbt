import sbt.Keys._
import play.sbt.PlaySettings

val catsVersion = "2.6.1"
lazy val root = (project in file("."))
  .enablePlugins(PlayService, PlayLayoutPlugin)
  .settings(
    name := "HeartBeatApplication",
    scalaVersion := "2.13.6",
    libraryDependencies ++= libraryDeps ++ dbDeps,
    scalacOptions ++= Seq(
      "-feature",
      "-deprecation",
      "-Xfatal-warnings"
    )
  )

val libraryDeps = Seq(
  guice,
  "org.joda" % "joda-convert" % "2.2.1",
  "net.logstash.logback" % "logstash-logback-encoder" % "6.2",
  "net.codingwell" %% "scala-guice" % "4.2.6",
  "org.scalatest" %% "scalatest" % "3.2.10" % Test,
  "org.scalatestplus" %% "mockito-3-4" % "3.2.10.0" % Test,
  "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test,
  "org.scalacheck" %% "scalacheck" % "1.15.4" % Test
)

val dbDeps = Seq(
  "com.typesafe.play" %% "play-slick" % "5.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "5.0.0",
  "com.github.tminglei" %% "slick-pg" % "0.19.7",
  "com.github.tminglei" %% "slick-pg_play-json" % "0.19.7"
)