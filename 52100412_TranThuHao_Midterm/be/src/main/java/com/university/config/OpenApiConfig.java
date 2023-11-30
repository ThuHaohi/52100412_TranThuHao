package com.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.annotations.info.Contact;


// Swagger-ui endpoint: http://localhost:8080/swagger-ui/index.html#
@Configuration
@OpenAPIDefinition(info = @Info(
		title = "Spring Ecommerce API",
		version = "v1",
		description = "A university project implemented with Spring Boot",
		contact = @Contact(name = "Thuy Duong", url = "https://www.facebook.com/duongthuypttd") 
	))
@SecurityScheme(
	    name = "bearerAuth",
	    type = SecuritySchemeType.HTTP,
	    bearerFormat = "JWT",
	    scheme = "bearer"
	)
public class OpenApiConfig {
//	@Bean
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .components(new Components())
//                .info(new Info()
//                        .title("Spring Ecommerce API")
//                        .description("A university project implemented with Spring Boot")
//                        .contact(new Contact().name("Thuy Duong").url("https://www.facebook.com/duongthuypttd")));
//    }
}
