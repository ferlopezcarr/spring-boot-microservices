package com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest

import com.ferlopezcarr.microservices.productService.TestcontainersConfiguration
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.model.CreateProductRequestDTO
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.daos.ProductDAO
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.models.ProductEntity
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigDecimal
import kotlin.test.Test

@Import(value = [TestcontainersConfiguration::class])
@SpringBootTest
@AutoConfigureMockMvc
class ProductControllerTest(
    @Autowired val mvc: MockMvc,
    @Autowired val productDAO: ProductDAO,
) {
    @BeforeEach
    fun setUp() {
        productDAO.deleteAll()
    }

    @Nested
    inner class CreateProductTests {
        @Test
        fun shouldCreateProduct() {
            val product = CreateProductRequestDTO("name", BigDecimal(9.99), "description")

            val result =
                mvc
                    .perform(
                        MockMvcRequestBuilders
                            .post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(
                                ObjectMapper().writeValueAsString(product),
                            ),
                    )

            result
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(product.name))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(product.price))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(product.description))
        }

        @Test
        fun shouldNotCreateDuplicatedProduct() {
            val product1 = ProductEntity(name = "name", price = BigDecimal(9.99))
            productDAO.save(product1)

            val product2 = CreateProductRequestDTO("name", BigDecimal(9.99), null)
            val result =
                mvc
                    .perform(
                        MockMvcRequestBuilders
                            .post("/product")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(
                                ObjectMapper().writeValueAsString(product2),
                            ),
                    )

            result.andExpect(status().isBadRequest())
        }
    }
}
