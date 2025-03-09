package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product

interface ProductRepository {
    fun save(product: NewProduct): Product
}
