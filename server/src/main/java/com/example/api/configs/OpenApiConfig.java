package com.example.api.configs;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){

        return new OpenAPI().info(new Info().title("VetConnect").version("v1").description("Api para o controle de agendamento e cliente de uma companinha veterinaria").termsOfService("")
                .license(new License()
                .name("")
                .url("")));
    }
}
