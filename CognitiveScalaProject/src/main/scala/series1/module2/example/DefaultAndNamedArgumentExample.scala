package series1.module2.example

object DefaultAndNamedArgumentExample {
  def main(args: Array[String]): Unit = {
    val sample = new Sample()
    //example of default parameter, midName is by default set to empty string
    sample.display("potassium", "permanganate")

    //example of named parameter
    sample.display(firstName = "methyl", lastName = "chloride")

    sample.display("ethyl", "methyl", "chloride")
  }
}

class Sample {
  def display(firstName: String, lastName: String, midName: String = ""): Unit = println(firstName + " " + midName + " " + lastName)
}
