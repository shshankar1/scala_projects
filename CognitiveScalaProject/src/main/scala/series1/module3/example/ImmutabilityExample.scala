package series1.module3.example

object ImmutabilityExample {
  def main(args: Array[String]): Unit = {
    @volatile var customer = Customer("Shashi","Shankar")
    println(customer)
    customer = customer.copy(lastName="XYZ")
    println(customer)
  }
}

case class Customer(firstName: String, lastName: String)
