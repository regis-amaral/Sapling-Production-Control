package dev.regis.rest.configurations;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

  @Value("http://localhost:8080")
  private String devUrl;

  @Bean
  public OpenAPI myOpenAPI() {
    Server devServer = new Server();
    devServer.setUrl(devUrl);
    devServer.setDescription("URL desenvolvimento");

    // Server prodServer = new Server();
    // prodServer.setUrl(prodUrl);
    // prodServer.setDescription("Server URL in Production environment");

    Contact contact = new Contact();
    contact.setEmail("amaral.regis@gmail.com");
    contact.setName("Regis Amaral");
    // contact.setUrl("#");

    // License mitLicense = new License().name("MIT License").url("#");

    Info info = new Info()
        .title("Sapling Production Control API")
        .version("1.0")
        .contact(contact)
        .description("Os endpoints e requisições abaixo são atendidos por essa API.");
        // .termsOfService("#")
        // .license(mitLicense);

    return new OpenAPI().info(info).servers(List.of(devServer));
  }
}