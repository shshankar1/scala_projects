package module3.example

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import util.Files

object DataFrameWithParquetExample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DataFrameWithParquetExample").setMaster("local")

    sparkConf.set("spark.sql.shuffle.partitions", "4")
    sparkConf.set("spark.app.id", "DataFrameWithParquetExample")

    val sparkContext = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sparkContext)

    try {
      val carriers = sqlContext.read.json("data/airline-flights/carriers.json")
      val outputPath = "output/carriers.parquet"

      Files.rmrf(outputPath)
      carriers.limit(100).write.parquet(outputPath)

      val dataFrame: DataFrame = sqlContext.read.parquet(outputPath)

      dataFrame.printSchema
      dataFrame.show

    } finally {
      sparkContext.stop()
    }
  }
}
