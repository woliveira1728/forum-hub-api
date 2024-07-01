package com.woliveira.forum_hub_api.config.springDoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT"))
                )
                .info(new Info()
                        .title("Hub Forum API")
                        .description("Rest API of a Forum application, containing CRUD functionalities for users, courses, and topics where users can respond to each topic")
                        .contact(new Contact()
                                .name("Oliveira, Wilson")
                                .email("woliveira1728@gmail.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://github.com/woliveira1728/forum-hub-challenger/blob/main/LICENSE"))
                );
    }

}
