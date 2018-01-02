package module3.example

import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}

object DataFrameWithCsvExample {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("DataFrameWithCsvExample").setMaster("local")

    sparkConf.set("spark.sql.shuffle.partitions", "4")
    sparkConf.set("spark.app.id", "DataFrameWithCsvExample")

    val sparkContext = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sparkContext)

    try {
      val dataFrame: DataFrame = sqlContext.read
        .format("com.databricks.spark.csv")
        .option("header", "true")
        .option("inferSchema", "true")
        .load("data/airline-flights/carriers.csv")

      dataFrame.printSchema
      dataFrame.show
    } finally {
      sparkContext.stop()
    }
  }
}
