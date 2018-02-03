package com.example.section3

import org.apache.spark.{SparkConf, SparkContext}

object TotalPurchaseAmountByCustomer {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("TotalPurchaseAmountByCustomer").setMaster("local")
    val sparkContext = new SparkContext(sparkConf)
    val data = sparkContext.textFile("data/customer-orders.csv")
    val customerPurchases = data.map(purchase => {
      val purch = purchase.split(",")
      (purch(0).toInt, purch(2).toFloat)
    })

    val totalPurchaseByCustomer = customerPurchases.reduceByKey(_ + _)
    //totalPurchaseByCustomer.collect().foreach(println)
    val sortByPurchaseAmount = totalPurchaseByCustomer.map(total => (total._2, total._1)).sortByKey()
    sortByPurchaseAmount.foreach(total => println(total._2, total._1))
  }
}
