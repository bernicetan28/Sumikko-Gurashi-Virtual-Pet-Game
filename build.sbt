ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.12.17"

lazy val root = (project in file("."))
  .settings(
    name := "RealAssignment",

    libraryDependencies ++= Seq(
      // https://mvnrepository.com/artifact/org.scalafx/scalafx
      "org.scalafx" %% "scalafx" % "8.0.192-R14",
      // https://mvnrepository.com/artifact/org.scalafx/scalafxml-core-sfx8
      "org.scalafx" %% "scalafxml-core-sfx8" % "0.5",
      // https://mvnrepository.com/artifact/com.lihaoyi/os-lib
      "com.lihaoyi" %% "os-lib" % "0.8.0",
      // https://mvnrepository.com/artifact/com.lihaoyi/upickle
      "com.lihaoyi" %% "upickle" % "2.0.0"


    )
  )
addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)

// https://mvnrepository.com/artifact/org.scalafx/scalafx
libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.192-R14"

// https://mvnrepository.com/artifact/org.scalafx/scalafxml-core-sfx8
libraryDependencies += "org.scalafx" %% "scalafxml-core-sfx8" % "0.5"


