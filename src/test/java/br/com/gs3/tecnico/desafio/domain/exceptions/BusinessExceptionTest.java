package br.com.gs3.tecnico.desafio.domain.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessExceptionTest {

    @Test
    public void testBusinessException_Constructor_ShouldSetMessage() {
        String expectedMessage = "Erro de negócio";
        BusinessException exception = new BusinessException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }
}

