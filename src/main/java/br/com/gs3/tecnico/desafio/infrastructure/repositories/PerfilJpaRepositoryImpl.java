//package br.com.gs3.tecnico.desafio.infrastructure.repositories;
//
//import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
//import br.com.gs3.tecnico.desafio.domain.repositories.PerfilRepository;
//import br.com.gs3.tecnico.desafio.infrastructure.adapters.PerfilAdapter;
//import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;
//import org.springframework.stereotype.Repository;
//
//import java.util.Optional;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Repository
//public class PerfilJpaRepositoryImpl implements PerfilRepository {
//
//    private final PerfilJpaRepository perfilJpaRepository;
//    private final PerfilAdapter perfilAdapter;
//
//    public PerfilJpaRepositoryImpl(PerfilJpaRepository perfilJpaRepository, PerfilAdapter perfilAdapter) {
//        this.perfilJpaRepository = perfilJpaRepository;
//        this.perfilAdapter = perfilAdapter;
//    }
//
//    @Override
//    public PerfilType save(PerfilType perfilType) {
//        PerfilEntity perfilEntity = perfilAdapter.toEntity(perfilType);
//        PerfilEntity savedEntity = perfilJpaRepository.save(perfilEntity);
//        return perfilAdapter.toDomain(savedEntity);
//    }
//
//    @Override
//    public Optional<PerfilType> findById(Long id) {
//        return perfilJpaRepository.findById(id)
//                .map(perfilAdapter::toDomain);
//    }
//
//    @Override
//    public PerfilType update(PerfilType perfilType) {
//        PerfilEntity perfilEntity = perfilAdapter.toEntity(perfilType);
//        PerfilEntity updatedEntity = perfilJpaRepository.save(perfilEntity);
//        return perfilAdapter.toDomain(updatedEntity);
//    }
//
//    @Override
//    public void delete(Long id) {
//        perfilJpaRepository.deleteById(id);
//    }
//
//    @Override
//    public Set<PerfilType> findAll() {
//        return perfilJpaRepository.findAll().stream()
//                .map(perfilAdapter::toDomain)
//                .collect(Collectors.toSet());
//    }
//}
