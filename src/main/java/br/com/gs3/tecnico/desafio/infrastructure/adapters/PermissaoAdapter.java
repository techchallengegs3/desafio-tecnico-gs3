package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;

import java.util.Set;

public interface PermissaoAdapter {
    PermissaoEntity toEntity(PermissaoType permissaoType);
    PermissaoType toDomain(PermissaoEntity permissaoEntity);

    Set<PermissaoEntity> toEntitySet(Set<PermissaoType> permissoes);
    Set<PermissaoType> toDomainSet(Set<PermissaoEntity> permissoes);
}
