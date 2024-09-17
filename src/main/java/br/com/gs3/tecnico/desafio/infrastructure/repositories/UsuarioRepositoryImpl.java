package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.UsuarioAdapter;
import br.com.gs3.tecnico.desafio.infrastructure.entities.UsuarioEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository usuarioJpaRepository;
    private final UsuarioAdapter usuarioAdapter;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository, UsuarioAdapter usuarioAdapter) {
        this.usuarioJpaRepository = usuarioJpaRepository;
        this.usuarioAdapter = usuarioAdapter;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UsuarioEntity entity = usuarioAdapter.toEntity(usuario);
        UsuarioEntity savedEntity = usuarioJpaRepository.save(entity);
        return usuarioAdapter.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        return usuarioJpaRepository.findById(id)
                .map(usuarioAdapter::toDomain);
    }

    @Override
    public Usuario update(Usuario usuario) {
        UsuarioEntity entity = usuarioAdapter.toEntity(usuario);
        UsuarioEntity updatedEntity = usuarioJpaRepository.save(entity);
        return usuarioAdapter.toDomain(updatedEntity);
    }

    @Override
    public void delete(Long id) {
        usuarioJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return usuarioJpaRepository.findById(id).isPresent();
    }

    @Override
    public List<Usuario> findAll() {
        List<UsuarioEntity> usuarioEntities = usuarioJpaRepository.findAll();
        return usuarioEntities.stream()
                .map(usuarioAdapter::toDomain)
                .collect(Collectors.toList());
    }
}
