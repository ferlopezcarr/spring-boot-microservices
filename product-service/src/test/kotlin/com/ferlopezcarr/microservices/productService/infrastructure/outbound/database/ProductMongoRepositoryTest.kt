package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database

import com.ferlopezcarr.microservices.productService.TestcontainersConfiguration
import com.ferlopezcarr.microservices.productService.application.exceptions.ProductAlreadyExistsException
import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.daos.ProductDAO
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports.ProductRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import java.math.BigDecimal
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@Import(TestcontainersConfiguration::class)
class ProductMongoRepositoryTest(
    @Autowired private val productDAO: ProductDAO,
    @Autowired private val productRepository: ProductRepository,
) {
    @BeforeEach
    fun setUp() {
        productDAO.deleteAll()
    }

    @Nested
    inner class SaveProductTests {
        @Test
        fun shouldSaveProductWithDescription() {
            val product = NewProduct(name = "name", price = BigDecimal(9.99), description = "description")

            val result = productRepository.save(product)

            assertNotNull(result.id)
            assertEquals(product.name, result.name)
            assertEquals(product.price, result.price)
            assertEquals(product.description, result.description)
        }

        @Test
        fun shouldSaveProductWithoutDescription() {
            val product = NewProduct(name = "name", price = BigDecimal(9.99), description = null)

            val result = productRepository.save(product)

            assertNotNull(result.id)
            assertEquals(product.name, result.name)
            assertEquals(product.price, result.price)
            assertEquals(product.description, result.description)
        }

        @Test
        fun shouldNotSaveProductsWithSameNames() {
            val product1 = NewProduct(name = "name", price = BigDecimal(9.99), description = null)
            productRepository.save(product1)
            val product2 = NewProduct(name = "name", price = BigDecimal(9.99), description = null)

            assertThrows<ProductAlreadyExistsException> { (productRepository.save(product2)) }
        }
    }
}
