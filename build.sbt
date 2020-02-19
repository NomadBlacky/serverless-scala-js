ThisBuild / scalaVersion := "2.13.1"
ThisBuild / organization := "dev.nomadblacky"
ThisBuild / organizationName := "NomadBlacky"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val lambda = (project in file("."))
  .enablePlugins(ScalaJSBundlerPlugin)
  .settings(
    name := "lambda",
    libraryDependencies ++= Seq(
      "net.exoego"                   %%% "aws-lambda-scalajs-facade" % "0.5.0",
      "com.softwaremill.sttp.client" %%% "core"                      % "2.0.0-RC11",
      "org.scalatest"                %%% "scalatest"                 % "3.1.0" % Test
    ),
    Compile / npmDependencies ++= Seq(
      "node-fetch"               -> "2.6.0",
      "abortcontroller-polyfill" -> "1.4.0",
      "fetch-headers"            -> "2.0.0"
    ),
    Compile / npmDevDependencies ++= Seq(
      "serverless" -> "1.64.0"
    ),
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
  )
