import _root_.java.time.{ZoneId, ZonedDateTime}
import com.cloudera.sparkts.models.ARIMA
import com.cloudera.sparkts.{DateTimeIndex, DayFrequency, TimeSeriesRDD}
import org.apache.spark.sql.types.{DoubleType, TimestampType}
import org.apache.spark.sql.{DataFrame, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.functions.unix_timestamp

object StockPredictor {
  val DAYS = 20

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TimeSeriesForecast").setMaster("local")
    sparkConf.set("spark.sql.shuffle.partitions", "4")
    val sparkContext = new SparkContext(sparkConf)
    val sqlContext = new SQLContext(sparkContext)
    val stockDF: DataFrame = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .option("inferSchema", "true")
      .load("data/Sreeleathers_Share_Price.csv")

    val priceDF: DataFrame = stockDF.select(stockDF("Symbol").as("Symbol"),
      stockDF("Date").as("Date"),
      stockDF("Close Price").as("ClosePrice"))

    priceDF.printSchema
    priceDF.show(10)

    import sqlContext.implicits._

    val finalDf: DataFrame = priceDF
      .withColumn("Symbol", priceDF("Symbol"))
      .withColumn("Price", priceDF("ClosePrice").cast(DoubleType))
      .withColumn("TimeStamp", unix_timestamp($"Date", "dd-MMM-yy").cast(TimestampType))
      .drop("Date").drop("ClosePrice")
      .sort("TimeStamp")

    finalDf.registerTempTable("preDf")

    finalDf.printSchema
    finalDf.show

    val minDate = finalDf.selectExpr("min(TimeStamp)").collect()(0).getTimestamp(0)
    val maxDate = finalDf.selectExpr("max(TimeStamp)").collect()(0).getTimestamp(0)

    val zone = ZoneId.systemDefault()

    val dtIndex = DateTimeIndex.uniformFromInterval(
      ZonedDateTime.of(minDate.toLocalDateTime, zone),
      ZonedDateTime.of(maxDate.toLocalDateTime, zone),
      new DayFrequency(1)
    )
    val tsRdd = TimeSeriesRDD.timeSeriesRDDFromObservations(dtIndex, finalDf, "TimeStamp", "Symbol", "Price")

    val df = tsRdd.mapSeries { vector => {
      val newVec = new org.apache.spark.mllib.linalg.DenseVector(vector.toArray.map(x => if (x.equals(Double.NaN)) 0 else x))
      val arimaModel = ARIMA.fitModel(1, 0, 0, newVec)
      val forecasted = arimaModel.forecast(newVec, DAYS)
      new org.apache.spark.mllib.linalg.DenseVector(forecasted.toArray.slice(forecasted.size - (DAYS + 1), forecasted.size - 1))
    }
    }.toDF("symbol", "values")
    df.registerTempTable("data")
    df.show

  }
}
