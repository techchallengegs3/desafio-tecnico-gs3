package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeletarUsuarioUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private DeletarUsuarioUseCase deletarUsuarioUseCase;

    public DeletarUsuarioUseCaseTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveExcluirUsuarioQuandoEncontrado() {
        Long userId = 1L;
        when(usuarioRepository.existsById(userId)).thenReturn(true);
        deletarUsuarioUseCase.execute(userId);
        verify(usuarioRepository, times(1)).delete(userId);
    }

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        Long userId = 1L;
        when(usuarioRepository.existsById(userId)).thenReturn(false);

        assertThrows(IllegalArgumentException.class, () -> {
            deletarUsuarioUseCase.execute(userId);
        });
    }
}

