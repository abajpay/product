package com.oracle.training.repository

import com.oracle.training.model.Product
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component

@Component
interface ProductRepository : CrudRepository<Product, Long> {
    fun findByName(name: String): List<Product>
}
