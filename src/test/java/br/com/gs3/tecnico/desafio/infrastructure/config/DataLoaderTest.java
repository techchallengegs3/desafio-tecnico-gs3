package br.com.gs3.tecnico.desafio.infrastructure.config;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import br.com.gs3.tecnico.desafio.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

public class DataLoaderTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DataLoader dataLoader;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRun() throws Exception {
        dataLoader.run();

        User expectedUser = User.builder()
                .nome("Admin")
                .email("admin@admin.com")
                .endereco("SQS 412 Bloco A")
                .telefone("(61) 98123-4567")
                .build();

        verify(userRepository).save(argThat(user ->
                user.getNome().equals("Admin") &&
                        user.getEmail().equals("admin@admin.com") &&
                        user.getEndereco().equals("SQS 412 Bloco A") &&
                        user.getTelefone().equals("(61) 98123-4567")
        ));
    }
}
