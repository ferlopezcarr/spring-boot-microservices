package com.ferlopezcarr.microservices.orderService.domain.models

import java.math.BigDecimal

data class NewOrder(
    val skuCode: String,
    val price: BigDecimal,
)
