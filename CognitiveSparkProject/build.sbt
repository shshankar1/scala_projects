scalaVersion := "2.11.8"

name := "Cognitive Spark Project"

val sparkVersion = "1.5.2"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core"              % sparkVersion withSources(),
  "org.apache.spark" %% "spark-streaming"         % sparkVersion withSources(),
  "org.apache.spark" %% "spark-sql"               % sparkVersion withSources(),
  "org.apache.spark" %% "spark-hive"              % sparkVersion withSources(),
  "org.apache.spark" %% "spark-streaming-twitter" % sparkVersion withSources(),
  "org.apache.spark" %% "spark-mllib"             % sparkVersion withSources(),
  //"com.typesafe.play" %% "play-json"              % "2.4.4" withSources(),
  "org.twitter4j"    %  "twitter4j-core"          % "3.0.3" withSources(),
  "com.databricks"   %% "spark-csv"               % "1.3.0"      withSources()
)

libraryDependencies += "org.twitter4j" % "twitter4j-core" % "3.0.3"
