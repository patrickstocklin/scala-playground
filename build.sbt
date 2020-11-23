name := "scala-playground"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies += "co.fs2" %% "fs2-core" % "2.4.4" // For cats 2 and cats-effect 2

// optional I/O library
libraryDependencies += "co.fs2" %% "fs2-io" % "2.4.4"

// optional reactive streams interop
libraryDependencies += "co.fs2" %% "fs2-reactive-streams" % "2.4.4"

// optional experimental library
libraryDependencies += "co.fs2" %% "fs2-experimental" % "2.4.4"