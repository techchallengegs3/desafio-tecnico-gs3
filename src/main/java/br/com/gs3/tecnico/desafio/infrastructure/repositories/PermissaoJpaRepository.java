package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissaoJpaRepository extends JpaRepository<PermissaoEntity, Long> {
    Optional<PermissaoEntity> findByCodigo(Long codigo);
}
