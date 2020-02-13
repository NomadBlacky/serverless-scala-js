ThisBuild / scalaVersion := "2.13.1"
ThisBuild / organization := "dev.nomadblacky"
ThisBuild / organizationName := "NomadBlacky"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val lambda = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(
    name := "lambda",
    libraryDependencies ++= Seq(
      "net.exoego" %%% "aws-lambda-scalajs-facade" % "0.3.3"
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
  )
