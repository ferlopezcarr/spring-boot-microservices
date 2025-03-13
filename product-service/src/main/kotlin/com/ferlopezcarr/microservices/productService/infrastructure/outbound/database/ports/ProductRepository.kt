package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import java.util.UUID

interface ProductRepository {
    fun getById(id: UUID): Product

    fun save(product: NewProduct): Product

    fun getAll(): List<Product>
}
