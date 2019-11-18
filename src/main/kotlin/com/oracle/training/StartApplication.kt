package com.oracle.training

import com.oracle.training.kafka.PriceChangePublisher
import com.oracle.training.model.Product
import com.oracle.training.repository.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication
open class StartApplication : CommandLineRunner {

    @Autowired
    private val productRepository: ProductRepository? = null
    @Autowired
    private val kafkaPublisher: PriceChangePublisher? = null

    @Throws(Exception::class)
    override fun run(vararg args: String) {

        log.info("StartApplication...")

        productRepository!!.save(Product("CrudeOil", 20.85))
        productRepository.save(Product("Petrol", 35.10))
        productRepository.save(Product("Disel", 30.50))

        println("\nfindAll()")
        productRepository.findAll().forEach { x -> println(x) }

        println("\nfindById(1L)")
        productRepository.findById(1L).ifPresent { x -> println(x) }

        println("\nfindByName('Node')")
        productRepository.findByName("Node").forEach { x -> println(x) }

        println("productService.findByName :: ==> " + productRepository.findByName("Petrol")[0].name!!)
        try {
            for (product in productRepository.findAll()) {
                kafkaPublisher!!.publishProductInfo(product)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

    }

    companion object {

        private val log = LoggerFactory.getLogger(StartApplication::class.java)

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(StartApplication::class.java, *args)
        }
    }
}
