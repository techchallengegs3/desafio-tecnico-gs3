package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.Perfil;
import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;

import java.util.Set;
import java.util.stream.Collectors;

public class PerfilAdapterImpl implements PerfilAdapter {

    @Override
    public PerfilEntity toEntity(PerfilType perfilType) {
        if (perfilType == null) {
            return null;
        }

        return PerfilEntity.builder()
                .tipo(perfilType)
                .build();
    }

    @Override
    public PerfilType toDomain(PerfilEntity perfilEntity) {
        if (perfilEntity == null) {
            return null;
        }

        return perfilEntity.getTipo();
    }

    @Override
    public Set<PerfilEntity> toEntitySet(Set<PerfilType> perfilTypes) {
        if (perfilTypes == null) {
            return null;
        }

        return perfilTypes.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PerfilType> toDomainSet(Set<PerfilEntity> perfilEntities) {
        if (perfilEntities == null) {
            return null;
        }

        return perfilEntities.stream()
                .map(this::toDomain)
                .collect(Collectors.toSet());
    }

    @Override
    public Perfil toDomainFromType(PerfilType perfilType) {
        if (perfilType == null) {
            return null;
        }

        return Perfil.builder()
                .tipo(perfilType)
                .build();
    }
}
