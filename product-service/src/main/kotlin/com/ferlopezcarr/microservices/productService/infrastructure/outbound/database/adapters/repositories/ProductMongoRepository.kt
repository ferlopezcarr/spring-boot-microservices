package com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.repositories

import com.ferlopezcarr.microservices.productService.application.exceptions.ProductAlreadyExistsException
import com.ferlopezcarr.microservices.productService.application.exceptions.ProductNotFoundException
import com.ferlopezcarr.microservices.productService.domain.models.NewProduct
import com.ferlopezcarr.microservices.productService.domain.models.Product
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.daos.ProductDAO
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.mappers.toDomainModel
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.adapters.mappers.toEntityModel
import com.ferlopezcarr.microservices.productService.infrastructure.outbound.database.ports.ProductRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
class ProductMongoRepository(
    private val productDAO: ProductDAO,
) : ProductRepository {
    /**
     * Find a product by its id
     *
     * @param id The id of the product to find
     * @return The product
     * @throws ProductNotFoundException if the product is not found
     */
    override fun getById(id: UUID): Product = productDAO.findById(id).orElseThrow { ProductNotFoundException(id) }.toDomainModel()

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

    /**
     * Get all products from the database
     *
     * @return A list of products
     */
    override fun getAll(): List<Product> = productDAO.findAll().map { it.toDomainModel() }
}
