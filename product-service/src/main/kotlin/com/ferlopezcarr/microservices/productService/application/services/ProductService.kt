package com.ferlopezcarr.microservices.productService.application.services

import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun create(product: NewProduct): Product = productRepository.save(product)
}
