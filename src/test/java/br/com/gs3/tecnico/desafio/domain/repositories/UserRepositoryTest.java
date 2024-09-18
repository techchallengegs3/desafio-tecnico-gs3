package br.com.gs3.tecnico.desafio.domain.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveAndFindUser() {
        User user = User.builder()
                .nome("John Doe")
                .email("john.doe@example.com")
                .endereco("123 Street")
                .telefone("1234567890")
                .build();

        user = userRepository.save(user);
        User foundUser = userRepository.findById(Math.toIntExact(user.getId())).orElse(null);

        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getNome()).isEqualTo("John Doe");
        assertThat(foundUser.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(foundUser.getEndereco()).isEqualTo("123 Street");
        assertThat(foundUser.getTelefone()).isEqualTo("1234567890");
    }

    @Test
    public void testFindAllUsers() {
        User user1 = User.builder()
                .nome("John Doe")
                .email("john.doe@example.com")
                .endereco("123 Street")
                .telefone("1234567890")
                .build();

        User user2 = User.builder()
                .nome("Jane Doe")
                .email("jane.doe@example.com")
                .endereco("456 Avenue")
                .telefone("0987654321")
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(2);
        assertThat(users).extracting(User::getNome).containsExactlyInAnyOrder("John Doe", "Jane Doe");
    }
}
