println("Hello, World!!")

val checkNotNull = (s: String) => {
  s match {
    case null => "n/a"
    case s: String => s
  }
}

checkNotNull(null)
checkNotNull("Shashi")

val compareWithZero = (num: Int) => {
  num.signum match {
    case 1 => "greater"
    case -1 => "less"
    case 0 => "same"
  }
}

compareWithZero(-2)
compareWithZero(89)

val colorMatchFunction = (color: String) => {
  color match {
    case "cyan" => "00ffff"
    case "magenta" => "00ff00"
    case "yellow" => "ffff00"
    case x => s"Didn't expect $x !"
  }
}

colorMatchFunction("cyan")

for(i<-1 to 100){
  print(s"${i},")
  if(i%5==0)println("")
}

for(i<-1 to 100 by 5){
  for(j<-i to (i+4))print(s"${j},")
  println()
}

for(i<- 1 to 100){
  i match{
    case i if(i%3==0)=>println("type")
    case i if(i%5==0)=>println("safe")
    case i if(i%5==0 && i%3==0)=>println("typesafe")
    case _ => println(s"${i}")
  }
}


