package com.disoraya.sales_system.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
    info = @Info(
        title = "SALES SYSTEM",
        description = "A RESTful API built for a company's sales system, enabling efficient management of products, clients, sales, and supervisors. It supports CRUD operations and integrates seamlessly with other applications, providing secure and scalable sales transactions",
        version = "1.0.0"
    ),
    externalDocs = @ExternalDocumentation(
        description = "Page for a more user-friendly way to understand HTTP status codes",
        url = "https://http.cat/"
    ),
    security = @SecurityRequirement(
        name = "Security Token"
    )
)
@SecurityScheme(
    name = "Security Token",
    description = "Authentication token for system administrator users",
    type = SecuritySchemeType.HTTP,
    paramName = HttpHeaders.AUTHORIZATION,
    in = SecuritySchemeIn.HEADER,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class SwaggerConfig {
}
