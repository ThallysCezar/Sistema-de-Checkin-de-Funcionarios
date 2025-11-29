package br.com.thallysprojetos.backenddesafio1.configs;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        return new OpenAPI().info(
                new Info()
                        .title("Desafio 1 do Grupo Moura, Backend")
                        .version("1.0")
                        .description("É uma API para um desafio do Grupo Moura, onde consiste em um Sistema de Checkin de Funcionários "
                                + "O código-fonte do projeto esta disponivel no GitHub: "
                                + "https://github.com/ThallysCezar/Sistema-de-Checkin-de-Funcionarios")
        );
    }

}