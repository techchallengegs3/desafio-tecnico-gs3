package br.com.gs3.tecnico.desafio.domain.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PermissaoRepository {
    PermissaoType save(PermissaoType permissaoType);
    Optional<PermissaoType> findById(Long id);
    PermissaoType update(PermissaoType permissaoType);
    void delete(Long id);
    long count();
}


