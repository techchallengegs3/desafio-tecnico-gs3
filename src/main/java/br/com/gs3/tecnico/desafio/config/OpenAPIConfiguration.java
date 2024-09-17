package br.com.gs3.tecnico.desafio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://localhost:8081");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Eduardo Almeida");
        myContact.setEmail("techchallengegs3@gmail.com");

        Info information = new Info()
                .title("API para Gestão de Usuários - GS3")
                .version("1.0")
                .description("Projeto criado para gestão de usuários.")
                .contact(myContact);
        return new OpenAPI().info(information).servers(List.of(server));
    }
}
