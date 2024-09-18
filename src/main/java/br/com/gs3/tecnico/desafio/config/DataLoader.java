package br.com.gs3.tecnico.desafio.config;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
import br.com.gs3.tecnico.desafio.infrastructure.repositories.PerfilJpaRepository;
import br.com.gs3.tecnico.desafio.infrastructure.repositories.PermissaoJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final PerfilJpaRepository perfilRepository;
    private final PermissaoJpaRepository permissaoRepository;

    public DataLoader(PerfilJpaRepository perfilRepository, PermissaoJpaRepository permissaoRepository) {
        this.perfilRepository = perfilRepository;
        this.permissaoRepository = permissaoRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        for (PermissaoType permissaoType : PermissaoType.values()) {
            PermissaoEntity permissaoEntity = PermissaoEntity.builder()
                    .descricao(permissaoType)
                    .build();
            permissaoRepository.save(permissaoEntity);
        }

        for (PerfilType perfilType : PerfilType.values()) {
            PerfilEntity perfilEntity = PerfilEntity.builder()
                    .tipo(perfilType)
                    .permissoes(new HashSet<>())
                    .build();
            perfilRepository.save(perfilEntity);
        }
        logger.info("DataLoader executado com sucesso: Perfis e permissões inseridos.");
    }
}




