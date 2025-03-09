package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.repositories

import com.ferlopezcarr.microservices.productService.application.exceptions.ProductAlreadyExistsException
import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.daos.ProductDAO
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.mappers.toDomainModel
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.mappers.toEntityModel
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports.ProductRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository

@Repository
class ProductMongoRepository(
    private val productDAO: ProductDAO,
) : ProductRepository {
    /**
     * Save a new product to the database
     *
     * @param product The product to save
     * @return The saved product
     * @throws ProductAlreadyExistsException if the product name is already in use
     */
    override fun save(product: NewProduct): Product =
        try {
            productDAO.save(product.toEntityModel()).toDomainModel()
        } catch (e: DuplicateKeyException) {
            throw ProductAlreadyExistsException(product.name)
        }
}
