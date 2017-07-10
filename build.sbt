organization := "com.nthportal"
name := "play-json-value-format"
description := "Utility for treating value classes as raw values for play-json."

val rawVersion = "0.2.0"
isSnapshot := false
version := rawVersion + {if (isSnapshot.value) "-SNAPSHOT" else ""}

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

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else None
}

publishMavenStyle := true
licenses := Seq("The Apache License, Version 2.0" -> url("https://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/NthPortal/play-json-value-format"))

pomExtra :=
  <scm>
    <url>https://github.com/NthPortal/play-json-value-format</url>
    <connection>scm:git:git@github.com:NthPortal/play-json-value-format.git</connection>
    <developerConnection>scm:git:git@github.com:NthPortal/play-json-value-format.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>NthPortal</id>
        <name>NthPortal</name>
        <url>https://github.com/NthPortal</url>
      </developer>
    </developers>

