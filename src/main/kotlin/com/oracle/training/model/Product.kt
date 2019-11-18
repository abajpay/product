package com.oracle.training.model

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0
    var name: String? = null
    var price: Double = 0.toDouble()

    constructor() {}

    constructor(name: String, price: Double) {
        this.name = name
        this.price = price
    }
}
