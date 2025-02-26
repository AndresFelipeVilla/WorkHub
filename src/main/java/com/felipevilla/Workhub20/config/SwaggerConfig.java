package com.felipevilla.Workhub20.config;

import org.springframework.http.HttpHeaders;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        title = "Workhub API",
        version = "1.0",
        description = "Workhub API Documentation",
        contact = @Contact(
            name = "Felipe Villa",
            email = "andresfelipevillapardo7@gmail.com",
            url = "https://www.linkedin.com/in/andres-felipe-villa-pardo/"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8082",
            description = "DEV SERVER"
        ),
        @Server(
            url = "https:EJEMPLONUEVO",
            description = "PROD SERVER"
        )
    },
    security = @SecurityRequirement(name = "Security Token")
)

@SecurityScheme(
    name = "Security Token",
    description ="Access Token for the API",
    type = SecuritySchemeType.HTTP,
    paramName = HttpHeaders.AUTHORIZATION,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {}
