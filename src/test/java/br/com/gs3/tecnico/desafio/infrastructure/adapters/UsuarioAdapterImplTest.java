package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.infrastructure.entities.UsuarioEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UsuarioAdapterImplTest {

    private final UsuarioAdapterImpl adapter = new UsuarioAdapterImpl();

    @Test
    void deveConverterUsuarioParaUsuarioEntity() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nome("Nome")
                .email("email@example.com")
                .senha("senha")
                .endereco("Endereço")
                .telefone("Telefone")
                .build();

        UsuarioEntity usuarioEntity = adapter.toEntity(usuario);

        assertEquals(usuario.getId(), usuarioEntity.getId());
        assertEquals(usuario.getNome(), usuarioEntity.getNome());
        assertEquals(usuario.getEmail(), usuarioEntity.getEmail());
        assertEquals(usuario.getSenha(), usuarioEntity.getSenha());
        assertEquals(usuario.getEndereco(), usuarioEntity.getEndereco());
        assertEquals(usuario.getTelefone(), usuarioEntity.getTelefone());
    }

    @Test
    void deveConverterUsuarioEntityParaUsuario() {
        UsuarioEntity usuarioEntity = UsuarioEntity.builder()
                .id(1L)
                .nome("Nome")
                .email("email@example.com")
                .senha("senha")
                .endereco("Endereço")
                .telefone("Telefone")
                .build();

        Usuario usuario = adapter.toDomain(usuarioEntity);

        assertEquals(usuarioEntity.getId(), usuario.getId());
        assertEquals(usuarioEntity.getNome(), usuario.getNome());
        assertEquals(usuarioEntity.getEmail(), usuario.getEmail());
        assertEquals(usuarioEntity.getSenha(), usuario.getSenha());
        assertEquals(usuarioEntity.getEndereco(), usuario.getEndereco());
        assertEquals(usuarioEntity.getTelefone(), usuario.getTelefone());
    }

    @Test
    void deveRetornarNullQuandoUsuarioForNulo() {
        UsuarioEntity usuarioEntity = adapter.toEntity(null);
        assertNull(usuarioEntity);
    }

    @Test
    void deveRetornarNullQuandoUsuarioEntityForNulo() {
        Usuario usuario = adapter.toDomain(null);
        assertNull(usuario);
    }
}
