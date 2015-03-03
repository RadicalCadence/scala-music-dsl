name := "radical-cadence"

version := "1.0"

scalaVersion := "2.11.5"

libraryDependencies ++= Seq("de.sciss" %% "scalamidi" % "0.2.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

scalacOptions := Seq("-feature", "-deprecation")

initialCommands in console := """
import radical_cadence.dsl._
import radical_cadence.dsl.parser._
"""
