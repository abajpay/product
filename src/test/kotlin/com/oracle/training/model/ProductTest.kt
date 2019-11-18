package com.training.project.service.model

import com.oracle.training.model.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ProductTest {

    @Test
    fun `Check empty Product instantiation`() {
        Product().apply {
            assertThat(name).isNull()
            assertThat(price).isEqualTo(0.0)
        }
    }

    @Test
    fun `Check Product instantiation with arguments`() {
        Product("Petrol", 25.10).apply {
            assertThat(name).isEqualTo("Petrol")
            assertThat(price).isEqualTo(25.10)
        }
    }

}
