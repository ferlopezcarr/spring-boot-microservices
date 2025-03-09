package com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.math.BigDecimal
import java.util.UUID

@Entity(name = "orders")
class OrderEntity(
    @Id
    val id: UUID = UUID.randomUUID(),
    @Column(name = "order_number", nullable = false)
    val orderNumber: UUID = UUID.randomUUID(),
    @Column(name = "sku_code", nullable = false)
    val skuCode: String,
    @Column(name = "price", nullable = false)
    val price: BigDecimal,
)
