package com.training.project.graphql

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.oracle.training.graphQL.Query
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID.randomUUID
import com.oracle.training.model.Product
import com.oracle.training.repository.ProductRepository
import java.util.*

class QueryTest {

    private lateinit var productOpt: Optional<Product>
    private var productRepository: ProductRepository = mock()
    private var query: Query = Query(productRepository)
    private lateinit var productList: List<Product>
    private lateinit var productListOne: List<Product>


    @BeforeEach
    fun setUp() {
        productOpt = Optional.ofNullable(Product("Petrol", 25.10))
        productList = listOf(Product("Petrol", 25.10), Product("MobilOil", 125.10),
            Product("CrudeOil", 15.10))
        productListOne = listOf(Product("Petrol", 25.10))
    }


    @Test
    fun `Check Product retrieval by Id`() {
        whenever(productRepository.findById(any())).thenReturn(productOpt)
        query.getProduct(1).apply {
            assertThat(id).isEqualTo(productOpt.get().id)
        }
    }

    @Test
    fun `Check Product retrieval by Name`() {
        whenever(productRepository.findByName(any())).thenReturn(productListOne)
        query.getProductsByName("Petrol").apply {
            assertThat(get(0).name).isEqualTo(productListOne.get(0).name)
        }
    }

    @Test
    fun `Check Products retrieval`() {
        whenever(productRepository.findAll()).thenReturn(productList)
        query.allProducts().apply {
            assertThat(size).isEqualTo(productList.size)
        }
    }
}