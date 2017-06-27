organization := "com.nthportal"
name := "play-json-value-format"
version := "1.0.0-SNAPSHOT"

scalaVersion := "2.12.2"

crossScalaVersions := Seq(
  "2.12.0",
  "2.12.1",
  "2.12.2"
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-json" % "2.6.0",
  "org.scalatest" %% "scalatest" % "3.0.1+" % Test
)
