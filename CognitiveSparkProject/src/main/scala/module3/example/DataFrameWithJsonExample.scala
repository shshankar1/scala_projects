package module3.example

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import util.Printer

object DataFrameWithJsonExample {
  val out = Console.out

  def main(args: Array[String]): Unit = {
    val sparkConfig = new SparkConf().setAppName("Spark Data Frames").setMaster("local")

    sparkConfig.set("spark.sql.shuffle.partitions", "4")
    sparkConfig.set("spark.app.id", "DataFrameWithJsonExample")

    val sparkContext = new SparkContext(sparkConfig)
    val sqlContext = new SQLContext(sparkContext)

    try {
      val data: DataFrame = sqlContext.read.json("data/airline-flights/carriers.json")

      data.printSchema()
      Printer(out, "Flights between airports, sorted by airports", data)

      //Printing out the records that failed to parse
      data.where(data("_corrupt_record").isNotNull).collect().foreach(println)
    } finally {
      sparkContext.stop()
    }
  }
}
