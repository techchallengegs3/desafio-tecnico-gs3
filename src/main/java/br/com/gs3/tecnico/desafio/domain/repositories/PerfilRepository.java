package br.com.gs3.tecnico.desafio.domain.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface PerfilRepository {
    PerfilType save(PerfilType perfilType);
    Optional<PerfilType> findById(Long id);
    PerfilType update(PerfilType perfilType);
    void delete(Long id);
    Set<PerfilType> findAll();
}