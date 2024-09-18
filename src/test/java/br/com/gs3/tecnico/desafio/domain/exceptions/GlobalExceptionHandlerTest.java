package br.com.gs3.tecnico.desafio.domain.exceptions;

import br.com.gs3.tecnico.desafio.controllers.TestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {TestController.class, GlobalExceptionHandler.class})
public class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testHandleBusinessException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/testBusinessException")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Mensagem de erro de negocio esperada"));
    }

    @Test
    public void testHandleHttpMessageNotReadableException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/testHttpMessageNotReadable")
                        .content("invalid json")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Erro ao ler a mensagem JSON: Erro de leitura"));
    }
}
