package com.example.section3

import org.apache.spark.{SparkConf, SparkContext}

object AverageFriendsByAge {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("AverageFriendsByAge")
    val sparkContext = new SparkContext(sparkConf)
    val lines = sparkContext.textFile("data/fakefriends.csv")
    val rdd = lines.map(line => {
      val text = line.split(",")
      (text(2).toInt, text(3).toInt)
    })
    val totalByAge = rdd.mapValues(x => (x, 1)).reduceByKey((x, y) => (x._1 + y._1, x._2 + y._2))
    val averageByAge = totalByAge.mapValues(x=>x._1/x._2)
    val result = averageByAge.collect()
    result.foreach(println)
  }
}
