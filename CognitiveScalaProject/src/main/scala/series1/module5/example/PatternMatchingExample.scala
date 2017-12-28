package series1.module5.example

import series1.module3.example.Customer

object PatternMatchingExample {
  def main(args: Array[String]): Unit = {
    println(isCustomer(Customer("Shashi","Shankar")))
    println(isCustomer("Shashi Shankar"))
  }

  //pattern matching example
  def isCustomer(some: Any): Boolean = {
    some match {
      case cust: Customer => true
      case _ => false
    }
  }
}
