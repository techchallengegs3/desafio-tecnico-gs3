package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ListarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private ListarUsuarioUseCase listarUsuarioUseCase;

    public ListarUsuarioUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornarTodosOsUsuarios() {
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .nome("Nome1")
                .email("email1@example.com")
                .senha("senha1")
                .endereco("Endereco1")
                .telefone("Telefone1")
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .nome("Nome2")
                .email("email2@example.com")
                .senha("senha2")
                .endereco("Endereco2")
                .telefone("Telefone2")
                .build();

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(usuario1);
        usuarios.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);
        List<Usuario> usuariosRetornados = listarUsuarioUseCase.execute();
        assertEquals(usuarios, usuariosRetornados);
    }
}

