package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EditarUsuarioInformacoesUseCaseTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private EditarUsuarioInformacoesUseCase editarUsuarioInformacoesUseCase;

    @Test
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", "Novo Telefone")
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void deveAtualizarInformacoesDoUsuario() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEndereco("Endereço Antigo");
        usuario.setTelefone("Telefone Antigo");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", "Novo Telefone");

        assertNotNull(updatedUsuario);
        assertEquals("Novo Endereço", updatedUsuario.getEndereco());
        assertEquals("Novo Telefone", updatedUsuario.getTelefone());
    }

    @Test
    void deveSalvarQuandoMudancasForemFeitas() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEndereco("Endereço Atual");
        usuario.setTelefone("Telefone Atual");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", "Novo Telefone");

        assertNotNull(updatedUsuario);
        assertEquals("Novo Endereço", updatedUsuario.getEndereco());
        assertEquals("Novo Telefone", updatedUsuario.getTelefone());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    void deveLancarExcecaoQuandoRepositorioFalhar() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEndereco("Endereço Atual");
        usuario.setTelefone("Telefone Atual");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenThrow(new RuntimeException("Falha ao salvar"));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", "Novo Telefone")
        );

        assertEquals("Falha ao salvar", exception.getMessage());
    }

    @Test
    void deveTratarRepositorioVazio() {
        when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", "Novo Telefone")
        );

        assertEquals("Usuário não encontrado", exception.getMessage());
    }

    @Test
    void deveTratarEnderecoNulo() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEndereco("Endereço Atual");
        usuario.setTelefone("Telefone Atual");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = editarUsuarioInformacoesUseCase.editarInformacoes(1L, null, "Novo Telefone");

        assertNotNull(updatedUsuario);
        assertEquals("Endereço Atual", updatedUsuario.getEndereco());
        assertEquals("Novo Telefone", updatedUsuario.getTelefone());
        verify(usuarioRepository, times(1)).save(updatedUsuario);
    }

    @Test
    void deveTratarTelefoneNulo() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEndereco("Endereço Atual");
        usuario.setTelefone("Telefone Atual");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario updatedUsuario = editarUsuarioInformacoesUseCase.editarInformacoes(1L, "Novo Endereço", null);

        assertNotNull(updatedUsuario);
        assertEquals("Novo Endereço", updatedUsuario.getEndereco());
        assertEquals("Telefone Atual", updatedUsuario.getTelefone());

        verify(usuarioRepository, times(1)).save(updatedUsuario);
    }

    @Test
    void deveLancarExcecaoQuandoEnderecoETelefoneForemNulos() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                editarUsuarioInformacoesUseCase.editarInformacoes(1L, null, null)
        );
        assertEquals("Endereço e telefone não podem ser nulos", exception.getMessage());
    }
}
