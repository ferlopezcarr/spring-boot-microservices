# Spring Boot Microservices

## Overview

This monorepo project is a Spring Boot-based microservices application, designed to demonstrate the use of microservices
architecture in a real-world scenario.

## Technologies Used

* **Programming Language:** Kotlin
* **Framework:** Spring Boot
* **Build Tool:** Gradle
* **Containerization:** Docker
* **Authentication:** Keycloak
* **Databases:**
    * MongoDB
    * PostgreSQL

## Important Tools

* **API**:
    * Spring API Gateway: Provides support for routing requests to the appropriate microservices
    * OpenAPI: Provides support for generating API code and documentation
* **Database**:
    * Liquibase: Provides support for database migration and versioning
* **Testing**:
    * Spring Boot Test: Provides support for testing Spring Boot applications
    * Junit 5: Provides support for writing unit tests
    * MockMvc: Provides support for mocking HTTP requests in tests
    * Mockito: Provides support for mocking objects in tests
    * Wiremock: Provides support for mocking HTTP requests in tests
    * Testcontainers: Provides support for testing the application using Docker containers

## Microservices

The application follows a microservices architecture, with the following components:

* **API Gateway:** Handles incoming requests and routes them to the appropriate microservices
* **Product Service:** Handles the creation and management of products
* **Order Service:** Handles the creation and management of orders
* **Inventory Service:** Handles the management of inventory for products
* **Notification Service:** Handles the sending of notifications for orders

Each microservice is built using **Hexagonal Architecture** and is responsible for a specific domain of the application.

## Application Documentation

The application provides the following documentation:

* **Swagger UI:** http://localhost:8080/swagger-ui.html
* **API Documentation:** http://localhost:8080/api-docs
