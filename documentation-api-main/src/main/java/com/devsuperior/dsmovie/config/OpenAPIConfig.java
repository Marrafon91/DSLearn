package com.devsuperior.dsmovie.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition
public class OpenAPIConfig {

    @Bean
    public OpenAPI dsmovieOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DSmovie API")
                        .description("DSmovie API description")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https/github.com/devsuperior/dsmovie/")));
    }
}
