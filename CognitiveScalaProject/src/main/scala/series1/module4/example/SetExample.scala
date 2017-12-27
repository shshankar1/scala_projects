package series1.module4.example

object SetExample {
  def main(args: Array[String]):Unit={
    val nums = Set(1,2,4,4,5)
    println(nums)
    println(nums(2))
    println(nums(7))
    val updatedNums = nums+7
    println(updatedNums)
    println(updatedNums.getClass)
  }
}
