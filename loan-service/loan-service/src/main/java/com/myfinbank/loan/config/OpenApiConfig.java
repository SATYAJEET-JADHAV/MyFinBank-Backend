package com.myfinbank.loan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI loanServiceOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("MyFinBank Loan Service API")
                        .description("Vehicle, Loan Application, Eligibility and Document APIs")
                        .version("1.0.0"));
    }
}
