//package br.com.gs3.tecnico.desafio.controllers;
//
//import br.com.gs3.tecnico.desafio.domain.entities.User;
//import br.com.gs3.tecnico.desafio.services.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(UserController.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserService userService;
//
//    @BeforeEach
//    public void setup() {
//        // Configuração padrão antes de cada teste
//    }
//
//    @Test
//    @WithMockUser // Adiciona um usuário simulado para evitar 403 Forbidden
//    public void testGetUserById() throws Exception {
//        mockMvc.perform(get("/api/list/1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    @WithMockUser // Adiciona um usuário simulado para evitar 403 Forbidden
//    public void testCreateUser() throws Exception {
//        when(userService.createUser(any(User.class))).thenReturn(new User());
//        mockMvc.perform(MockMvcRequestBuilders.post("/api/create")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("{\"nome\": \"John Doe\", \"email\": \"john.doe@example.com\", \"endereco\": \"123 Street\", \"telefone\": \"1234567890\"}"))
//                .andExpect(MockMvcResultMatchers.status().isCreated());
//    }
//}
