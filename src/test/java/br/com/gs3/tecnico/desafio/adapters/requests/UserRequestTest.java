package br.com.gs3.tecnico.desafio.adapters.requests;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRequestTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidUserRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setNome("John Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setEndereco("123 Street");
        userRequest.setTelefone("1234567890");

        Set<jakarta.validation.ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertTrue(violations.isEmpty(), "Não deve haver violações para um UserRequest válido");
    }

    @Test
    public void testInvalidUserRequest_missingNome() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("john.doe@example.com");
        userRequest.setEndereco("123 Street");
        userRequest.setTelefone("1234567890");

        Set<jakarta.validation.ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        assertTrue(violations.iterator().next().getMessage().contains("O nome é obrigatório"), "A mensagem de erro deve ser sobre o nome obrigatório");
    }

    @Test
    public void testInvalidUserRequest_invalidEmail() {
        UserRequest userRequest = new UserRequest();
        userRequest.setNome("John Doe");
        userRequest.setEmail("invalid-email");
        userRequest.setEndereco("123 Street");
        userRequest.setTelefone("1234567890");

        Set<jakarta.validation.ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        assertTrue(violations.iterator().next().getMessage().contains("Email inválido"), "A mensagem de erro deve ser sobre o email inválido");
    }

    @Test
    public void testInvalidUserRequest_missingEndereco() {
        UserRequest userRequest = new UserRequest();
        userRequest.setNome("John Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setTelefone("1234567890");

        Set<jakarta.validation.ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        assertTrue(violations.iterator().next().getMessage().contains("O endereço é obrigatória"), "A mensagem de erro deve ser sobre o endereço obrigatório");
    }

    @Test
    public void testInvalidUserRequest_missingTelefone() {
        UserRequest userRequest = new UserRequest();
        userRequest.setNome("John Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setEndereco("123 Street");

        Set<jakarta.validation.ConstraintViolation<UserRequest>> violations = validator.validate(userRequest);
        assertEquals(1, violations.size(), "Deve haver uma violação de validação");
        assertTrue(violations.iterator().next().getMessage().contains("O telefone é obrigatória"), "A mensagem de erro deve ser sobre o telefone obrigatório");
    }
}
