package com.hoangbuix.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
//
//@OpenAPIDefinition(
//        info =
//        @Info(
//                title = "Inventory Management API",
//                description = "This api provides Inventory Management",
//                version = "1.0",
//                contact = @Contact(name = "James Bui", email = "buihoang9b8@gmail.com")),
//        security = @SecurityRequirement(name = "bearerAuth"))
//@SecurityScheme(type = SecuritySchemeType.HTTP, name = "bearerAuth", scheme = "bearer")

@Configuration
public class OpenAPIConfig {
        @Bean
        public OpenAPI customOpenAPI() {
                return new OpenAPI()
                        .servers(Lists.newArrayList(
                                new Server().url("http://localhost:8080"),
                                new Server().url("https://google.com")
                        ))
                        // info
                        .info(new Info().title(" Application API")
                                .description("Sample OpenAPI 3.0")
                                .contact(new Contact()
                                        .email("hoangbui9b8@gmail.com")
                                        .name("James")
                                        .url("https://fb.com/"))
                                .license(new License()
                                        .name("Apache 2.0")
                                        .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                                .version("1.0.0"));
        }
}
