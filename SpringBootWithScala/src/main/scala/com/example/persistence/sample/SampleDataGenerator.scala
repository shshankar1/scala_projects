package com.example.persistence.sample

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.example.persistence.dao.ProductRepository
import com.example.persistence.model.Product

@Component
class SampleDataGenerator @Autowired()(val productRepository: ProductRepository) {

  def generate(): Unit = {
    productRepository.save(new Product(id=1L, "Santoor Soap", 25, "2018-01-12"))
    productRepository.save(new Product(id=2L, "Milk", 18, "2018-01-15"))
    productRepository.save(new Product(id=3L, "Almond", 235, "2018-01-18"))
    productRepository.save(new Product(id=4L, "Water Filter", 2678, "2017-12-15"))
    productRepository.save(new Product(id=5L, "Knife", 124, "2018-01-18"))
  }
}
