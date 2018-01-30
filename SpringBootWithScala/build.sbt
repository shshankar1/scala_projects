scalaVersion := "2.11.8"

name := "Spring Boot With Scala Project"

val springBootVersion = "1.5.3.RELEASE"
val h2Version = "1.4.195"

libraryDependencies ++= Seq(
  "org.springframework.boot" % "spring-boot-starter" % springBootVersion,
  "org.springframework.boot" % "spring-boot-starter-data-jpa" % springBootVersion,
  "org.springframework.boot" % "spring-boot-autoconfigure" % springBootVersion,
  "org.springframework.boot" % "spring-boot-dependencies" % springBootVersion,
  "com.h2database" % "h2" % h2Version
)