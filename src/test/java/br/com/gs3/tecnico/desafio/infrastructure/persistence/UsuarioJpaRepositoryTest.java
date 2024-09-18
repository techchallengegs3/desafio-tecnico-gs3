package br.com.gs3.tecnico.desafio.infrastructure.persistence;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import br.com.gs3.tecnico.desafio.infrastructure.persistence.UsuarioJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UsuarioJpaRepositoryTest {

    @Autowired
    private UsuarioJpaRepository usuarioJpaRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@example.com")
                .endereco("123 Street")
                .telefone("1234567890")
                .build();

        User savedUser = usuarioJpaRepository.save(user);
        Optional<User> foundUser = usuarioJpaRepository.findById(savedUser.getId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getNome()).isEqualTo("John Doe");
        assertThat(foundUser.get().getEmail()).isEqualTo("john.doe@example.com");
        assertThat(foundUser.get().getEndereco()).isEqualTo("123 Street");
        assertThat(foundUser.get().getTelefone()).isEqualTo("1234567890");
    }

    @Test
    public void testDeleteUser() {
        User user = User.builder()
                .nome("Jane Doe")
                .email("jane.doe@example.com")
                .endereco("456 Avenue")
                .telefone("0987654321")
                .build();

        User savedUser = usuarioJpaRepository.save(user);
        usuarioJpaRepository.deleteById(savedUser.getId());
        Optional<User> foundUser = usuarioJpaRepository.findById(savedUser.getId());
        assertThat(foundUser).isNotPresent();
    }
}
