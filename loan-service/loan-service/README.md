# MyFinBank Loan Service

Enhanced loan-service for MyFinBank microservices project.

## Features

- Vehicle management
- Loan application enhancement
- Eligibility engine
- Customer Service OpenFeign client
- Document upload to local `uploads/` folder
- Swagger/OpenAPI
- Lombok
- Global exception handling
- Header-based role security for API Gateway integration

## Run Order

1. discovery-server
2. api-gateway
3. auth-service
4. customer-service
5. loan-service

## Important Gateway Headers

API Gateway should forward:

- X-User-Email
- X-User-Role
- X-User-Id

## Swagger

http://localhost:8082/swagger-ui.html
