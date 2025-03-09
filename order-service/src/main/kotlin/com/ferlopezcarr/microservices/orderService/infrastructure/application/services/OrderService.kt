package com.ferlopezcarr.microservices.orderService.infrastructure.application.services

import com.ferlopezcarr.microservices.orderService.domain.models.NewOrder
import com.ferlopezcarr.microservices.orderService.domain.models.Order
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.ports.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun create(order: NewOrder): Order = orderRepository.save(order)
}
