package com.example.section3

import org.apache.spark.{SparkConf, SparkContext}

object AverageFriendsByFirstName {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("AverageFriendsByFirstName").setMaster("local")
    val sparkContext = new SparkContext(sparkConf);
    val data = sparkContext.textFile("data/fakefriends.csv")
    val transformedData = data.map(line => {
      val values = line.split(",")
      (values(1), values(3).toInt)
    })
    val totalByAge = transformedData.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    val averageByName = totalByAge.mapValues(x => x._1 / x._2)
    val result = averageByName.collect()
    result.foreach(println)
  }
}
