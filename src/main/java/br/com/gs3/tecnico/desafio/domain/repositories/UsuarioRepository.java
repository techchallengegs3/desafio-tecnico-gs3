package br.com.gs3.tecnico.desafio.domain.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UsuarioRepository {
    Usuario save(Usuario usuario);
    Optional<Usuario> findById(Long id);
    Usuario update(Usuario usuario);
    void delete(Long id);
    boolean existsById(Long id);
    List<Usuario> findAll();
}
