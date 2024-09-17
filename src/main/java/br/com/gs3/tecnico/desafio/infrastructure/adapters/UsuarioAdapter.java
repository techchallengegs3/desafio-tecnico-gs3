package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.infrastructure.entities.UsuarioEntity;

public interface UsuarioAdapter {
    UsuarioEntity toEntity(Usuario usuario);
    Usuario toDomain(UsuarioEntity usuarioEntity);
}
