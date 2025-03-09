package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.util.UUID

@Document(value = "products")
data class ProductEntity(
    @Id
    val id: UUID? = UUID.randomUUID(),
    @Indexed(unique = true)
    val name: String,
    val description: String? = null,
    val price: BigDecimal,
)
