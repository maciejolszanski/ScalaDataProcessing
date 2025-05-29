ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "JobPostingAnalysis",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.19" % "test",
    testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
  )
