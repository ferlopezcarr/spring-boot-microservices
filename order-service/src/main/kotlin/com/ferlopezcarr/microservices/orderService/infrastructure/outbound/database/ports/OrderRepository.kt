package com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.ports

import com.ferlopezcarr.microservices.orderService.domain.models.NewOrder
import com.ferlopezcarr.microservices.orderService.domain.models.Order

interface OrderRepository {
    fun save(order: NewOrder): Order
}
