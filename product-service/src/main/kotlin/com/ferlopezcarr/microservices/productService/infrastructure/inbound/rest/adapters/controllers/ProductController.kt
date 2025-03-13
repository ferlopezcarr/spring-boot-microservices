package com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.adapters.controllers

import com.ferlopezcarr.microservices.productService.application.exceptions.ProductAlreadyExistsException
import com.ferlopezcarr.microservices.productService.application.exceptions.ProductNotFoundException
import com.ferlopezcarr.microservices.productService.application.services.ProductService
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.adapters.mappers.toDomainModel
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.adapters.mappers.toResponseDTO
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.api.ProductApi
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.api.ProductsApi
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.model.CreateProductRequestDTO
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.model.ProductResponseDTO
import io.swagger.v3.oas.annotations.Parameter
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/")
class ProductController(
    private val productService: ProductService,
) : ProductApi,
    ProductsApi {
    override fun getProduct(
        @Parameter(description = "ID of the product to retrieve", required = true) @PathVariable(
            value = "id",
        ) id: UUID,
    ): ResponseEntity<ProductResponseDTO> =
        try {
            val product = productService.getById(id).toResponseDTO()
            ResponseEntity.status(HttpStatus.OK).body(product)
        } catch (e: ProductNotFoundException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }

    override fun createProduct(
        @Parameter(
            description = "",
            required = true,
        ) @Valid @RequestBody createProductRequestDTO: CreateProductRequestDTO,
    ): ResponseEntity<ProductResponseDTO> =
        try {
            val product = productService.create(createProductRequestDTO.toDomainModel()).toResponseDTO()
            ResponseEntity.status(HttpStatus.CREATED).body(product)
        } catch (e: ProductAlreadyExistsException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }

    override fun getAllProducts(): ResponseEntity<List<ProductResponseDTO>> =
        try {
            val products = productService.getAll().map { it.toResponseDTO() }
            ResponseEntity.status(HttpStatus.OK).body(products)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
}
