package series1.module5.example

import scala.util.{Try, Success, Failure}

object HandlingFailureExample {
  def main(args: Array[String]): Unit = {
    println("10".toInt)
    //println("ksd".toInt)
    val result = Try("ksd".toInt)
    println(result)

    println(makeInt("20"))
    println(makeInt("hgj"))
  }

  def toInt(s: String): Int = {
    try {
      s.toInt
    } catch {
      case _: NumberFormatException => 0
    }
  }

  def makeInt(s:String):Int= Try(s.toInt) match{
    case Success(n) => n
    case Failure(_) => 0
  }
}
