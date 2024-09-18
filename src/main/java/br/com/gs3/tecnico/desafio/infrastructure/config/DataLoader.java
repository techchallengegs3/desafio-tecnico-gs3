package br.com.gs3.tecnico.desafio.infrastructure.config;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import br.com.gs3.tecnico.desafio.domain.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Carregar usuário administrador
        User admin = User.builder()
                .nome("Admin")
                .email("admin@admin.com")
                .endereco("SQS 412 Bloco A")
                .telefone("(61) 98123-4567")
                .build();
        userRepository.save(admin);
        logger.info("DataLoader executado com sucesso: Perfis e permissões inseridos.");
    }
}
