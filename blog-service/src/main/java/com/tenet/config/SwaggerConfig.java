package com.tenet.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        List<Server> servers = new ArrayList<>();
        servers.add(new Server().url("https://www.qrcoddy.com/blog"));
        servers.add(new Server().url("http://localhost:8081"));
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("auth",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY).in(SecurityScheme.In.HEADER).name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("auth"))
                .servers(servers);
    }
}
