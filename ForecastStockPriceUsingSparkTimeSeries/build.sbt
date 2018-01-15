import sbt.complete.Parsers._

scalaVersion := "2.11.8"

javaVersion := "1.8.0"
javacOptions ++= Seq("-source", "1.8", "-target", "1.8", "-Xlint")

name := "Forecast Stock Price using Spark TimeSeries library"

val sparkVersion = "1.6.0"

//resolvers ++= Seq("Cloudera Repository" at "https://repository.cloudera.com/artifactory/cloudera-repos/")

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"              % sparkVersion withSources(),
  "org.apache.spark" %% "spark-streaming"         % sparkVersion withSources(),
  "org.apache.spark" %% "spark-sql"               % sparkVersion withSources(),
  "org.apache.spark" %% "spark-hive"              % sparkVersion withSources(),
  "org.apache.spark" %% "spark-mllib"             % sparkVersion withSources(),
  "com.databricks"   %% "spark-csv"               % "1.3.0"      withSources(),
  "com.cloudera.sparkts" % "sparkts"              % "0.4.0"      withSources()
)