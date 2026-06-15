package com.myfinbank.customer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.*;
import org.springframework.context.annotation.*;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customerServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyFinBank Customer Service API")
                        .description("Customer Profile and KYC Management APIs")
                        .version("1.0.0"));
    }
}