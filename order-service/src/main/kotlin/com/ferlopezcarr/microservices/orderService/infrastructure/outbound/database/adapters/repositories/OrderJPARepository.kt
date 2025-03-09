package com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.repositories

import com.ferlopezcarr.microservices.orderService.domain.models.NewOrder
import com.ferlopezcarr.microservices.orderService.domain.models.Order
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.daos.OrderDAO
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.mappers.toDomainModel
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.mappers.toEntityModel
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.ports.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderJPARepository(
    private val orderDAO: OrderDAO,
) : OrderRepository {
    /**
     * Save a new order to the database
     *
     * @param order The order to save
     * @return The saved order
     */
    override fun save(order: NewOrder): Order = orderDAO.save(order.toEntityModel()).toDomainModel()
}
