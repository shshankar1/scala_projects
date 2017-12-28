package series1.module4.example

object HigherOrderFunctionExample {
  def main(args: Array[String]): Unit = {
    val range = 1 to 5
    val sum = range.reduce((current, accumulator) => current + accumulator)
    println(sum)

    val sumUsingFoldLeft = range.foldLeft(0)((acc, curr) => acc + curr)
    //one more way of foldleft
    //val sumUsingFoldLeft = range.foldLeft(0)(_+_)
    println(sumUsingFoldLeft)

    //product function on range
    println(range.product)

    //find function
    println(range.find(ele => ele == 4))
    println(range.find(ele => ele == 7))

    //group by function
    println(range.groupBy(ele => ele % 2))

    //takeWhile and dropWhile function
    println(range.takeWhile(ele => ele < 3))
    println(range.dropWhile(ele => ele < 3))
  }
}
