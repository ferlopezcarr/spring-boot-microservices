package com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.daos

import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.models.OrderEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface OrderDAO : JpaRepository<OrderEntity, UUID>
