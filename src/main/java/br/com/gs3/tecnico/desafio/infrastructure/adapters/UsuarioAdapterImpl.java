package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.infrastructure.entities.UsuarioEntity;

public class UsuarioAdapterImpl implements UsuarioAdapter {

    @Override
    public UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        return UsuarioEntity.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
//              .perfil(PerfilAdapter.toEntity(usuario.getPerfil())) TODO ajustar questão do PerfilAdapter
                .endereco(usuario.getEndereco())
                .telefone(usuario.getTelefone())
                .build();
    }

    @Override
    public Usuario toDomain(UsuarioEntity usuarioEntity) {
        if (usuarioEntity == null) {
            return null;
        }

        return Usuario.builder()
                .id(usuarioEntity.getId())
                .nome(usuarioEntity.getNome())
                .email(usuarioEntity.getEmail())
                .senha(usuarioEntity.getSenha())
//              .perfil(PerfilAdapter.toDomain(entity.getPerfil())) TODO ajustar questão do PerfilAdapter
                .endereco(usuarioEntity.getEndereco())
                .telefone(usuarioEntity.getTelefone())
                .build();
    }

}
