"hello"
val x = 5 * 20
val amount = x + 10
val amt = {
  val x = 5 * 20;
  x + 10
}

val temp = {
  val a = 1;
  {
    val b = a * 2;
    {
      b + 4
    }
  }
}

if (47 % 3 > 0) println("Not a multiple of 3")

val result = if (false) "what does this returns?"

val s = 10;
val t = 20

//val max =if(s>t)s else t

val max = (s > t) match {
  case true => s
  case false => t
}

val status = 500

val message = status match {
  case 200 =>
    "ok"
  case 400 => {
    println("ERROR - we called the service incorrectly")
    "error"
  }
  case 500 => {
    println("ERROR: service encountered an error")
    "error"
  }
}

val day = "MON"

val kind = day match {
  case "MON" | "TUES" | "WED" | "THRS" | "FRI" =>
    "weekday"
  case "SAT" | "SUN" =>
    "weekend"
  case _ => "Invalid day"
}

val response: String = "500"

response match {
  case null => println("received a null response")
  case s: String => println(s"Received ${s}")
}

val a: Int = 5

val b: Any = a

b match {
  case x: String => s"'x'"
  case x: Double => f"$x%.2f"
  case x: Float => f"$x%.2f"
  case x: Long => s"${x}l"
  case x: Int => s"${x}i"
}

val days = for (i <- 1 to 7) yield s"Day $i:"

for (day <- days) println(day)

val threes = for (i <- 1 to 20 if (i % 3 == 0)) yield i

val quota = "Faith,Hope,,Charity"

quota.split(",").foreach(text => {
  if (text != null && text.size > 0)
    println(text)
})

for (i <- 1 to 5; j <- 1 to i) {
  println(s"(${i},${j})")
}

val powerOf2 = for (i <- 0 to 8; pow = 1 << i) yield pow

var q=10; while(q>0)q-=1

var n=100
do {
  println(s"Here I am, n=$n"); n-=1;
} while(n>0)




