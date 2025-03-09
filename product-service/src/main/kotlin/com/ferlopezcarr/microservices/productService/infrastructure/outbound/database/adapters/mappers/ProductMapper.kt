package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.mappers

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.models.ProductEntity

internal fun NewProduct.toEntityModel(): ProductEntity =
    ProductEntity(
        name = this.name,
        description = this.description,
        price = this.price,
    )

internal fun ProductEntity.toDomainModel(): Product =
    Product(
        id = this.id!!,
        name = this.name,
        description = this.description,
        price = this.price,
    )
