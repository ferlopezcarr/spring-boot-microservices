package com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.adapters.controllers

import com.ferlopezcarr.microservices.orderService.infrastructure.application.services.OrderService
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.adapters.mappers.toDomainModel
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.adapters.mappers.toResponseDTO
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.api.OrderApi
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.model.CreateOrderRequestDTO
import com.ferlopezcarr.microservices.orderService.infrastructure.inbound.rest.model.OrderResponseDTO
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class OrderController(
    private val orderService: OrderService,
) : OrderApi {
    override fun createOrder(
        @Parameter(
            description = "",
            required = true,
        ) @Valid @RequestBody createOrderRequestDTO: CreateOrderRequestDTO,
    ): ResponseEntity<OrderResponseDTO> =
        try {
            val product = orderService.create(createOrderRequestDTO.toDomainModel()).toResponseDTO()
            ResponseEntity.status(HttpStatus.CREATED).body(product)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
}
