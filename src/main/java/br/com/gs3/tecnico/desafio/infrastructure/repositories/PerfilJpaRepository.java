package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilJpaRepository extends JpaRepository<PerfilEntity, Long> {

}
