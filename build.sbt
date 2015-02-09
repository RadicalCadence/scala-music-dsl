name := "radical-cadence"

version := "1.0"

scalaVersion := "2.11.5"

val scalazVersion = "7.1.1"
    
libraryDependencies ++= Seq("org.scalaz" %% "scalaz-core" % scalazVersion,
  "de.sciss" %% "scalamidi" % "0.2.0",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

initialCommands in console := """
import radical_cadence.dsl.structures._
import radical_cadence.dsl.parser._
"""
