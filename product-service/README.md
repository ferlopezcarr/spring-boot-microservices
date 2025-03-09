# Product Service

This microservice handles the management of products in the application.

## Running the microservice

Run the required containers using the docker compose command from the service's root directory:

```bash
docker-compose up -d
```

Run the microservice using the `product-service local` run configuration or by running:

```bash
./gradlew bootRun --args='--spring.profiles.active=local'
```
