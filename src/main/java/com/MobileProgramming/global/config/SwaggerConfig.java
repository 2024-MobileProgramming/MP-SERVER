package com.MobileProgramming.global.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(name = "Authorization", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER, bearerFormat = "JWT", scheme = "Bearer")
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI api() {
        Info info = new Info().title("Garbit API Docs")
                .version("v1.0")
                .description("Garbit 서비스 API 명세서 입니다.");
        return new OpenAPI().info(info);
    }
}