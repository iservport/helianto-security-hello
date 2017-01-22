organization := "org.helianto"

version := "1.0.0.RELEASE"

sbtVersion := "0.13.9"

scalaVersion := "2.11.8"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(commonSettings)
  .settings(
    name := "helianto-security-hello",
    mainClass in Compile := Some("org.helianto.sample.Application"),
    packageName in Docker := "mvps-156214/helianto-security-hello",
    dockerBaseImage := "azul/zulu-openjdk:8",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8999),
    dockerExposedVolumes := Seq("/opt/data"),
    dockerRepository := Some("us.gcr.io"),
    libraryDependencies ++= Seq(
      "org.helianto"            %% "helianto-material-skin"       % "1.3.0.RELEASE",
      "org.scalatest"           %% "scalatest"                    % "3.0.0"        % "test",
      "org.mockito"              % "mockito-all"                  % "1.10.19"      % "test"
    )
  )

lazy val commonSettings = Seq(
  resolvers  ++= Seq( "Helianto Releases"  at "s3://maven.helianto.org/release" ),
  publishTo   := Some("Helianto Releases"  at "s3://maven.helianto.org/release"),
  credentials += Credentials(Path.userHome / ".sbt" / ".s3credentials"),
  publishMavenStyle := true
)
