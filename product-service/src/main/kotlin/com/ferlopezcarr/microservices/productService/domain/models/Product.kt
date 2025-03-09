package com.ferlopezcarr.microservices.productService.domain.models

import java.math.BigDecimal
import java.util.UUID

data class Product(
    val id: UUID,
    val name: String,
    val description: String? = null,
    val price: BigDecimal,
)
