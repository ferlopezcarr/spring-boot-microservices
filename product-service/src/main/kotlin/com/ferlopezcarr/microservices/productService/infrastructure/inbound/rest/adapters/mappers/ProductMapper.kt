package com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.adapters.mappers

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.model.CreateProductRequestDTO
import com.ferlopezcarr.microservices.productService.infrastructure.inbound.rest.model.ProductResponseDTO

internal fun CreateProductRequestDTO.toDomainModel(): NewProduct = NewProduct(name, description, price)

internal fun Product.toResponseDTO(): ProductResponseDTO = ProductResponseDTO(id, name, price, description)
