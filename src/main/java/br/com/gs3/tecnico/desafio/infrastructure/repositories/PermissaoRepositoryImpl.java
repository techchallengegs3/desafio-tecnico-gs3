package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.domain.repositories.PermissaoRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.PermissaoAdapter;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    private final PermissaoJpaRepository permissaoJpaRepository;
    private final PermissaoAdapter permissaoAdapter;

    public PermissaoRepositoryImpl(PermissaoJpaRepository permissaoJpaRepository, PermissaoAdapter permissaoAdapter) {
        this.permissaoJpaRepository = permissaoJpaRepository;
        this.permissaoAdapter = permissaoAdapter;
    }

    @Override
    public PermissaoType save(PermissaoType permissaoType) {
        PermissaoEntity permissaoEntity = permissaoAdapter.toEntity(permissaoType);
        PermissaoEntity savedEntity = permissaoJpaRepository.save(permissaoEntity);
        return permissaoAdapter.toDomain(savedEntity);
    }

    @Override
    public Optional<PermissaoType> findById(Long id) {
        return permissaoJpaRepository.findById(id)
                .map(permissaoAdapter::toDomain);
    }

    @Override
    public PermissaoType update(PermissaoType permissaoType) {
        Optional<PermissaoEntity> existingEntity = permissaoJpaRepository.findById(permissaoType.getCodigo());
        if (existingEntity.isPresent()) {
            PermissaoEntity entity = permissaoAdapter.toEntity(permissaoType);
            entity.setId(existingEntity.get().getId());
            PermissaoEntity updatedEntity = permissaoJpaRepository.save(entity);
            return permissaoAdapter.toDomain(updatedEntity);
        } else {
            throw new IllegalArgumentException("Permissão não encontrada para atualização.");
        }
    }

    @Override
    public void delete(Long id) {
        permissaoJpaRepository.deleteById(id);
    }

    @Override
    public long count() {
        return permissaoJpaRepository.count();
    }
}
