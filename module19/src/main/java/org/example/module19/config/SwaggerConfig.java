package org.example.module19.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("Bearer Authorisation"))
                .components(new Components()
                        .addSecuritySchemes("Bearer Authorisation",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .bearerFormat("JWT")
                                        .scheme("bearer")));
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("Public Api")
                .pathsToMatch("/api/v1/signup", "/api/v1/login")
                .build();
    }

    @Bean
    public GroupedOpenApi userApiV1() {
        return GroupedOpenApi.builder()
                .group("User Api v1")
                .pathsToMatch("/api/v1/**")
                .pathsToExclude("/api/v1/signup", "/api/v1/login")
                .build();
    }

    @Bean
    public GroupedOpenApi userApiV2() {
        return GroupedOpenApi.builder()
                .group("User Api v2")
                .pathsToMatch("/api/v2/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("Admin Api v1")
                .pathsToMatch("/admin/**")
                .build();
    }
}
