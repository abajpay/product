package com.training.project.graphql

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.oracle.training.graphQL.Mutation
import com.oracle.training.model.Product
import com.oracle.training.repository.ProductRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID

class MutationTest {

    private lateinit var product: Product
    private var productRepository: ProductRepository = mock()
    private var mutation: Mutation = Mutation(productRepository)

    @BeforeEach
    fun setUp() {
        product = Product("Petrol", 25.10)
    }

}