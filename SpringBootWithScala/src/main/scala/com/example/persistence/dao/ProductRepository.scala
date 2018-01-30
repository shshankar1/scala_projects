package com.example.persistence.dao

import org.springframework.data.repository.CrudRepository
import com.example.persistence.model.Product
import java.lang.Long

trait ProductRepository extends CrudRepository[Product, Long] {

}
