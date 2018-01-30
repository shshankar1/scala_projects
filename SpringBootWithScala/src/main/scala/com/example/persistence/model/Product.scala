package com.example.persistence.model

import javax.persistence.{Entity, GeneratedValue, Id}

import org.omg.CosNaming.NamingContextPackage.NotEmpty

import scala.annotation.meta.field
import scala.beans.BeanProperty

@Entity
class Product(@(Id@field)
              @(GeneratedValue@field)
              @BeanProperty
              var id: Long,

              @BeanProperty
              @(NotEmpty@field)
              var name: String,

              @BeanProperty
              @(NotEmpty@field)
              var price: Float,

              @BeanProperty
              @(NotEmpty@field)
              var mfgDate: String
             ) {
  def this() = this(1L, "", 0.0f, "")

  override def toString = s"Product($id, $name, $price, $mfgDate)"
}
