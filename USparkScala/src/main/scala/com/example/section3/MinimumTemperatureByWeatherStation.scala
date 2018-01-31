package com.example.section3

import org.apache.spark.{SparkConf, SparkContext}
import scala.math.min

//imput file has format:
// station_id,date,eventType,Temperature
object MinimumTemperatureByWeatherStation {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("MinimumTemperatureByWeatherStation").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val data = sparkContext.textFile("data/1800.csv")
    val transforedData = data.map(rec => {
      val record = rec.split(",")
      val tempInFarenhite = record(3).toFloat * 0.1f * (9.0f / 5.0f) + 32.0f
      (record(0), record(2), tempInFarenhite)
    })
    val minTemperatureData = transforedData.filter(x => x._2 == "TMIN")
    val tempByStation = minTemperatureData.map(x => (x._1, x._3))
    val minTempByStation = tempByStation.reduceByKey((x, y) => min(x, y))
    minTempByStation.collect().foreach(println)
  }
}
