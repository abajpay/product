package com.oracle.training.graphQL

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.oracle.training.kafka.PriceChangePublisher
import com.oracle.training.model.Product
import com.oracle.training.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class Mutation : GraphQLMutationResolver {

    @Autowired
    public var productRepository: ProductRepository? = null
    @Autowired
    public val priceChangePublisher: PriceChangePublisher? = null

    constructor(productRepository: ProductRepository){
        this.productRepository = productRepository
    }

    @Transactional
    open fun createProduct(name: String, price: Double): Product {

        val product = Product()
        product.name = name
        product.price = price
        priceChangePublisher!!.publishProductInfo(product)
        return productRepository!!.save(product)
    }

    @Transactional
    open fun updateProduct(id: Long, price: Double): Product {

        val product = if (productRepository!!.findById(id).isPresent) productRepository!!.findById(id).get() else null
        if (product != null) {
            priceChangePublisher!!.publishPriceChange(product, price)
            product.price = price
            return productRepository!!.save(product)
        } else {
            return Product()
        }
    }

    @Transactional
    open fun renameProduct(id: Long, name: String): Product {

        val product = if (productRepository!!.findById(id).isPresent) productRepository!!.findById(id).get() else null
        if (product != null) {
            priceChangePublisher!!.publishNameChange(product, name)
            product.name = name
            return productRepository!!.save(product)
        } else {
            return Product()
        }
    }

    @Transactional
    open fun deleteProduct(id: Long): Boolean {
        productRepository!!.deleteById(id)
        return true
    }
}
