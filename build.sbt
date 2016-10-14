
import sbt.Keys._
import sbtassembly.AssemblyKeys

val akkaVersion = "2.4.7"
val circeVersion = "0.4.1"


lazy val commonSettings = Seq(
  organization := "de.codecentric",
  name := "life.bootstrap",
  version := "1.0",
  scalaVersion := "2.11.8",
  resolvers ++= Seq(
    "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"
  ),

  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    "org.specs2" % "specs2_2.11" % "3.7" % "test" pomOnly()
  )
)




lazy val life: Project = (project in file("life.bootstrap")).
                         settings(commonSettings: _*).
                         settings(
                           resolvers += Resolver.bintrayRepo("hseeberger", "maven"),

                           libraryDependencies ++= Seq(
                             "com.typesafe.akka" %% "akka-http-core" % akkaVersion,
                             "com.typesafe.akka" %% "akka-http-experimental" % akkaVersion,
                             "de.heikoseeberger" % "akka-http-circe_2.11" % "1.7.0",
                             "com.typesafe.akka" %% "akka-http-testkit" % akkaVersion % "test"
                           ),

                           libraryDependencies ++= Seq(
                             "io.circe" %% "circe-core",
                             "io.circe" %% "circe-generic",
                             "io.circe" %% "circe-parser"
                           ).map(_ % circeVersion)
                         ).
                         settings(
                           mainClass in assembly := Some("de.codecentric.life.GameOfLifeActor"),
                           assemblyJarName in assembly := "life.jar"
                         )

lazy val dockerSettings = Seq(
  docker <<= (docker dependsOn (AssemblyKeys.assembly in life)),
  dockerfile in docker := {
    val artifact: File = (assembly in life).value
    val artifactTargetPath = s"/app/${artifact.name}"

    new sbtdocker.mutable.Dockerfile {
      from("openjdk:8-jre")
      copy(artifact, artifactTargetPath)
      cmd("java", "-jar", artifactTargetPath)
      expose(8080)
    }
  },
  buildOptions in docker := BuildOptions()
)

lazy val life_bootstrap = (project in file(".")).
                          enablePlugins(DockerPlugin).
                          settings(commonSettings: _*).
                          settings(dockerSettings: _*).
                          aggregate(life)




