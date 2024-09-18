package br.com.gs3.tecnico.desafio.infrastructure.persistence;

import br.com.gs3.tecnico.desafio.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<User, Long> {
}

