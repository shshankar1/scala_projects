package series1.module4.example


object TupleExample {
  def main(args: Array[String]): Unit = {
    val tuple = (3, 4, 8, 9)
    println(tuple._2)
    val (first, second, third, fourth) = tuple
    println(third)
  }
}
