package series1.module3.example

//scala provide multiple synthetic methods like equals, hashcode, toString, copy
//override it as per convenience
object SyntheticMethodsExample {
  def main(args: Array[String]): Unit = {
    val s1 = new Student("1", "Shashi")
    val s2 = new Student("1", "Shashi")
    //double equal comparison for object equality
    println(s1 == s2)
    println(s1.hashCode)
    println(s2.hashCode)
  }
}

class Student(val id: String, val name: String) {
  override def equals(obj: scala.Any): Boolean = {
    obj match{
      case stud:Student => stud.id.equals(this.id)
      case _ => false
    }
  }

  override def hashCode(): Int = id.hashCode
}

