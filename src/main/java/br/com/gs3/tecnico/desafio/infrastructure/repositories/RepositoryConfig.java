package br.com.gs3.tecnico.desafio.infrastructure.repositories;

import br.com.gs3.tecnico.desafio.domain.repositories.PerfilRepository;
import br.com.gs3.tecnico.desafio.domain.repositories.PermissaoRepository;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public PerfilAdapter perfilAdapter() {
        return new PerfilAdapterImpl();
    }

//    @Bean
//    public PerfilRepository perfilRepository(PerfilJpaRepository perfilJpaRepository, PerfilAdapter perfilAdapter) {
//        return new PerfilJpaRepositoryImpl(perfilJpaRepository, perfilAdapter);
//    }

    @Bean
    public PermissaoAdapter permissaoAdapter() {
        return new PermissaoAdapterImpl();
    }

    @Bean
    public PermissaoRepository permissaoRepository(PermissaoJpaRepository permissaoJpaRepository, PermissaoAdapter permissaoAdapter) {
        return new PermissaoRepositoryImpl(permissaoJpaRepository, permissaoAdapter);
    }

    @Bean
    public UsuarioAdapter usuarioAdapter() {
        return new UsuarioAdapterImpl();
    }

    @Bean
    public UsuarioRepository usuarioRepository(UsuarioJpaRepository usuarioJpaRepository, UsuarioAdapter usuarioAdapter) {
        return new UsuarioRepositoryImpl(usuarioJpaRepository, usuarioAdapter);
    }
}
