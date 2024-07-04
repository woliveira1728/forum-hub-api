package com.woliveira.forum_hub_api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url = "/", description = "Defalt Server URL")})
@SpringBootApplication
public class HubForumApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(HubForumApiApplication.class, args);

	}

}
