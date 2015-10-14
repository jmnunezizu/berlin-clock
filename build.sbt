organization := "io.jmnunezizu"

name := "scala-for-the-impatient"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

javacOptions ++= Seq("-source", "1.8")

libraryDependencies ++= Seq(
  "org.specs2" %% "specs2-core" % "3.6.4" % "test"
)

scalacOptions in Test ++= Seq("-Yrangepos")
