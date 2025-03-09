package com.ferlopezcarr.microservices.orderService.domain.models

import java.math.BigDecimal
import java.util.UUID

data class Order(
    val id: UUID,
    val orderNumber: UUID,
    val skuCode: String,
    val price: BigDecimal,
)
