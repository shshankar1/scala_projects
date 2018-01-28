scalaVersion := "2.11.8"

name := "USparkScala Project"

val sparkVersion = "1.6.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"              % sparkVersion withSources()
)