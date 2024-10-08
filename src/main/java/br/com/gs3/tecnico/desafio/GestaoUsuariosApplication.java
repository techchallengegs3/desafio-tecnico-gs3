package br.com.gs3.tecnico.desafio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "br.com.gs3.tecnico.desafio.*")
public class GestaoUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestaoUsuariosApplication.class, args);
    }

}
