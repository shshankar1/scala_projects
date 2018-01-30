package com.example

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.{CommandLineRunner, SpringApplication}
import org.springframework.boot.autoconfigure.SpringBootApplication
import persistence.dao.ProductRepository
import persistence.sample.SampleDataGenerator
import scala.collection.JavaConversions._


@SpringBootApplication
class ApplicationMain @Autowired()(val sampleDataGenerator: SampleDataGenerator, val productRepository: ProductRepository) extends CommandLineRunner {
  val LOGGER = LoggerFactory.getLogger(classOf[ApplicationMain])

  override def run(args: String*): Unit = {
    LOGGER.info("This is spring boot application written in Scala")
    sampleDataGenerator.generate()

    val products = productRepository.findAll()
    products.foreach(product=>LOGGER.info(product.toString))
  }
}

object ApplicationMain {
  def main(args: Array[String]): Unit = {
    SpringApplication.run(classOf[ApplicationMain], args: _*)
  }
}
