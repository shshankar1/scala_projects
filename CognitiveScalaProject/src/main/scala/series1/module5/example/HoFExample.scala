package series1.module5.example

object HoFExample {
  def main(args: Array[String]): Unit = {
    val range = 1 to 8
    val map = range.map(i => (1 to i).map(j => i * j))
    println(map)

    val flatMap = range.flatMap(i => (1 to i).map(j => j * i))
    println(flatMap)

    //for expression
    val modMap = for {
      i <- range
      j <- 1 to i
    } yield i * j
    println(modMap)
  }
}
