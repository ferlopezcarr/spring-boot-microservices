package com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.mappers

import com.ferlopezcarr.microservices.orderService.domain.models.NewOrder
import com.ferlopezcarr.microservices.orderService.domain.models.Order
import com.ferlopezcarr.microservices.orderService.infrastructure.outbound.database.adapters.models.OrderEntity

fun NewOrder.toEntityModel(): OrderEntity = OrderEntity(skuCode = skuCode, price = price)

fun OrderEntity.toDomainModel(): Order = Order(id, orderNumber, skuCode, price)
