package com.example.section2

import scala.annotation.tailrec
import scala.collection.mutable.ArrayBuffer

object FibonacciExample {
  def main(args: Array[String]): Unit = {
    val fibonacciAtIndexFour = fibonacci(8)
    println(fibonacciAtIndexFour)

    println(displayFibonacci(8))

  }

  def fibonacci(index: Int): BigInt = {
    @tailrec
    def inner(firstElem: BigInt, secondElem: BigInt, currIndex: Int): BigInt = {
      if (currIndex == index) secondElem
      else
        inner(secondElem, firstElem + secondElem, currIndex + 1)
    }
    inner(0, 1, 1)
  }

  def displayFibonacci(index: Int): ArrayBuffer[Int] = {
    @tailrec
    def inner(arr: ArrayBuffer[Int], currIndex: Int): ArrayBuffer[Int] = {
      if (currIndex == index) return arr
      else
        arr += arr(currIndex) + arr(currIndex - 1)
      inner(arr, currIndex + 1)
    }
    inner(ArrayBuffer(0, 1), 1)
  }
}
