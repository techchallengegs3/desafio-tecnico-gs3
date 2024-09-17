package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.Perfil;
import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;

import java.util.Set;

public interface PerfilAdapter {
    PerfilEntity toEntity(PerfilType perfilType);
    PerfilType toDomain(PerfilEntity perfilEntity);
    Perfil toDomainFromType(PerfilType perfilType);
    Set<PerfilEntity> toEntitySet(Set<PerfilType> perfilTypes);
    Set<PerfilType> toDomainSet(Set<PerfilEntity> perfilEntities);

}
