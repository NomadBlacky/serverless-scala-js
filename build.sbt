ThisBuild / scalaVersion := "2.13.1"
ThisBuild / organization := "dev.nomadblacky"
ThisBuild / organizationName := "NomadBlacky"
ThisBuild / version := "0.1.0-SNAPSHOT"

lazy val lambda = (project in file("."))
  .enablePlugins(ScalaJSBundlerPlugin, ScalablyTypedConverterPlugin)
  .settings(
    name := "lambda",
    useYarn := true,
    libraryDependencies ++= Seq(
        "net.exoego"    %%% "aws-lambda-scalajs-facade" % "0.11.0",
        "com.lihaoyi"   %%% "upickle"                   % "1.2.2",
        "org.scalatest" %%% "scalatest"                 % "3.2.2" % Test
      ),
    Compile / npmDependencies ++= Seq(
        "typed-rest-client" -> "1.5.0"
      ),
    Compile / npmDevDependencies ++= Seq(
        "serverless" -> "1.64.0"
      ),
    webpackConfigFile := Some(baseDirectory.value / "webpack.config.js")
  )
