package com.example.chapter1

object ImmutableDataExample {
  def main(args: Array[String]): Unit = {
    val weekdays: List[String] = List("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")
    println(weekdays)
    val modifiedWeekdays = weekdays.map(_ == "Monday")
    println(modifiedWeekdays)
  }

}
