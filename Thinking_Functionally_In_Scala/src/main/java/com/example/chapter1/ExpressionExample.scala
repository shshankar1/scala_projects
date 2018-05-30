package com.example.chapter1

object ExpressionExample {
  def main(args: Array[String]): Unit = {
    val radius = 2
    val area = {
      val PI = 3.1
      println(s"Inside scope 1, PI=$PI");
      {
        val PI = 3.14
        println(s"Inside scope 2, PI=$PI")
        PI * radius * radius
      }
      PI * radius * radius
    }
    println(area)
  }
}
