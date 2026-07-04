package com.aicommerce.product.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI productApi() {

        return new OpenAPI()

                .info(

                        new Info()

                                .title("AI Commerce Product Service")

                                .description("""
                                        Product Microservice

                                        Hexagonal Architecture
                                        Java 21
                                        Spring Boot 3
                                        PostgreSQL
                                        Flyway
                                        Docker
                                        AWS Ready
                                        """)

                                .version("v1.0.0")

                                .contact(

                                        new Contact()

                                                .name("AI Commerce")

                                                .email("contato@aicommerce.com")

                                                .url("https://github.com/seuusuario")

                                )

                                .license(

                                        new License()

                                                .name("MIT")

                                )

                )

                .externalDocs(

                        new ExternalDocumentation()

                                .description("Project Documentation")

                                .url("https://github.com/seuusuario/product-service")

                );

    }

}