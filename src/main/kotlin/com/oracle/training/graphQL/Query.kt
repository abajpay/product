package com.oracle.training.graphQL

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.oracle.training.model.Product
import com.oracle.training.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.ArrayList
import java.util.stream.Collectors
import java.util.stream.StreamSupport

@Service
open class Query : GraphQLQueryResolver {

    @Autowired
    private var productRepository: ProductRepository? = null

    constructor(productRepository: ProductRepository){
        this.productRepository = productRepository
    }

    fun getProduct(id: Long): Product {
        return if (productRepository!!.findById(id).isPresent) productRepository!!.findById(id).get() else Product()
    }

    fun getProductsByName(name: String): List<Product> {
        return if (productRepository!!.findByName(name) != null) productRepository!!.findByName(name) else ArrayList()
    }

    fun allProducts(): List<Product> {
        return productRepository!!.findAll().toList()
    }
}
