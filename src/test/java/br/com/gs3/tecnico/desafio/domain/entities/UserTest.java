package br.com.gs3.tecnico.desafio.domain.entities;

import br.com.gs3.tecnico.desafio.domain.exceptions.BusinessException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testSetNome_ValidName_ShouldSetName() {
        User user = new User();
        String validName = "John Doe";
        user.setNome(validName);
        assertEquals(validName, user.getNome());
    }

    @Test
    public void testSetNome_NullName_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setNome(null));
        assertEquals("Nome não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetNome_EmptyName_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setNome(""));
        assertEquals("Nome não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetEmail_ValidEmail_ShouldSetEmail() {
        User user = new User();
        String validEmail = "john.doe@example.com";
        user.setEmail(validEmail);
        assertEquals(validEmail, user.getEmail());
    }

    @Test
    public void testSetEmail_NullEmail_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setEmail(null));
        assertEquals("Email não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetEmail_InvalidEmail_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setEmail("invalid-email"));
        assertEquals("Email inválido", thrown.getMessage());
    }

    @Test
    public void testSetEndereco_ValidEndereco_ShouldSetEndereco() {
        User user = new User();
        String validEndereco = "123 Main St";
        user.setEndereco(validEndereco);
        assertEquals(validEndereco, user.getEndereco());
    }

    @Test
    public void testSetEndereco_NullEndereco_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setEndereco(null));
        assertEquals("Endereco não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetEndereco_EmptyEndereco_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setEndereco(""));
        assertEquals("Endereco não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetTelefone_ValidTelefone_ShouldSetTelefone() {
        User user = new User();
        String validTelefone = "123-456-7890";
        user.setTelefone(validTelefone);
        assertEquals(validTelefone, user.getTelefone());
    }

    @Test
    public void testSetTelefone_NullTelefone_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setTelefone(null));
        assertEquals("Telefone não pode ser nulo ou vazio", thrown.getMessage());
    }

    @Test
    public void testSetTelefone_EmptyTelefone_ShouldThrowException() {
        User user = new User();
        BusinessException thrown = assertThrows(BusinessException.class, () -> user.setTelefone(""));
        assertEquals("Telefone não pode ser nulo ou vazio", thrown.getMessage());
    }
}

