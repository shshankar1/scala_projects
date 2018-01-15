scalaVersion := "2.11.8"

name := "Programming in Scala -2nd Edition Examples"

libraryDependencies ++= Seq(
  "org.apache.spark" % "spark-mllib_2.11" % "1.5.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.4",
  "com.typesafe.akka" %% "akka-slf4j" % "2.3.4"
)
