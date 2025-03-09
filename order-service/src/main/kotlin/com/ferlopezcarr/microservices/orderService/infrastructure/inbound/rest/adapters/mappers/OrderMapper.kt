package com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.adapters.mappers

import com.ferlopezcarr.microservices.orderService.domain.models.NewOrder
import com.ferlopezcarr.microservices.orderService.domain.models.Order
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.model.CreateOrderRequestDTO
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.model.OrderResponseDTO

internal fun CreateOrderRequestDTO.toDomainModel(): NewOrder = NewOrder(skuCode, price)

internal fun Order.toResponseDTO(): OrderResponseDTO = OrderResponseDTO(id, orderNumber, skuCode, price)
