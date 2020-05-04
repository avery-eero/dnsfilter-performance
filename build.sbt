import NativePackagerHelper._

name := "dnsfilter-performance"

enablePlugins(GatlingPlugin)
enablePlugins(JavaAppPackaging)
enablePlugins(UniversalPlugin)

scalaVersion := "2.12.10"

libraryDependencies ++= Seq(
  // Normally, you only need these for Gatling.
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1" % "test,it",
  "io.gatling" % "gatling-test-framework" % "3.3.1" % "test,it",

  // However, with universal packaging, we also need to include it in the main compile.
  "io.gatling.highcharts" % "gatling-charts-highcharts" % "3.3.1",
  "io.gatling" % "gatling-test-framework" % "3.3.1",
)

publishArtifact in (Test, packageBin) := true
publishArtifact in Gatling := true

mappings in Universal ++= Seq(
  new File(baseDirectory.value.getAbsolutePath + "/src/test/resources/application.conf") -> "src/test/resources/application.conf",
  new File(baseDirectory.value.getAbsolutePath + "/src/test/resources/gatling.conf") -> "src/test/resources/gatling.conf",
  new File(baseDirectory.value.getAbsolutePath + "/src/test/resources/logback.xml") -> "src/test/resources/logback.xml",
  new File(baseDirectory.value.getAbsolutePath + "/src/test/resources/recorder.conf") -> "src/test/resources/recorder.conf"
)

mainClass in Compile := Some("io.gatling.app.Gatling")
(managedClasspath in Runtime) += (packageBin in Test).value

scalacOptions := Seq(
  "-encoding",
  "UTF-8",
  "-target:jvm-1.8",
  "-deprecation",
  "-feature",
  "-unchecked",
  "-language:implicitConversions",
  "-language:postfixOps"
)
