package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class PermissaoAdapterImpl implements PermissaoAdapter{

    @Override
    public PermissaoEntity toEntity(PermissaoType permissaoType) {
        return PermissaoEntity.builder()
                .codigo(permissaoType.getCodigo())
                .descricao(permissaoType.getDescricao())
                .build();
    }

    @Override
    public PermissaoType toDomain(PermissaoEntity permissaoEntity) {
        return PermissaoType.fromCodigo(permissaoEntity.getCodigo());
    }

    @Override
    public Set<PermissaoEntity> toEntitySet(Set<PermissaoType> permissoes) {
        return permissoes.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PermissaoType> toDomainSet(Set<PermissaoEntity> permissoes) {
        return permissoes.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }
}