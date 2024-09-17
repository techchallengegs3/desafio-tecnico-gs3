package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.infrastructure.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
}

