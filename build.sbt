name := "music-dsl"

version := "1.0"

scalaVersion := "2.11.4"

val scalazVersion = "7.1.0"
    
libraryDependencies ++= Seq("org.scalaz" %% "scalaz-core" % scalazVersion,
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.3",
  "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
)

initialCommands in console := "import music_dsl.structures._"
