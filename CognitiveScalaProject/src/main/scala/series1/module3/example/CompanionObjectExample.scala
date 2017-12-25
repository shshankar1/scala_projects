package series1.module3.example

object CompanionObjectExample {
  def main(args: Array[String]): Unit = {
    val e1 = Employee("1","Shashi")
    println(e1)
  }
}

class Employee(val id: String, val name: String = Employee.defaultName) {

  override def toString = s"Employee($id, $name)"
}
//companion object private fields can be referred from class.
object Employee {
  private val defaultName = ""

  def apply(id: String, name: String): Employee = new Employee(id, name)

}