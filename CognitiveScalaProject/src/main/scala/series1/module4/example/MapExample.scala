package series1.module4.example

object MapExample {
  def main(args: Array[String]): Unit = {
    val numRange = 1 to 5
    val charRange = 'a' to 'g'
    val vector = numRange.zip(charRange)
    val map = vector.toMap
    println(map(3))
    println(map.get(9))
  }
}
