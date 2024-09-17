//package br.com.gs3.tecnico.desafio.config;
//
//import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
//import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
//import br.com.gs3.tecnico.desafio.domain.repositories.PerfilRepository;
//import br.com.gs3.tecnico.desafio.domain.repositories.PermissaoRepository;
//import br.com.gs3.tecnico.desafio.infrastructure.adapters.PermissaoAdapter;
//import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;
//import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.Set;
//import java.util.stream.Collectors;
//
//@Configuration
//public class DataLoader {
//
//    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);
//
//    private final PermissaoAdapter permissaoAdapter;
//
//    public DataLoader(PermissaoAdapter permissaoAdapter) {
//        this.permissaoAdapter = permissaoAdapter;
//    }
//
//    @Bean
//    public CommandLineRunner loadData(PerfilRepository perfilRepository, PermissaoRepository permissaoRepository) {
//        return args -> {
//
//            if (permissaoRepository.count() == 0) {
//                PermissaoType[] permissaoTypes = PermissaoType.values();
//                for (PermissaoType tipo : permissaoTypes) {
//                    permissaoRepository.save(tipo);
//                }
//
//                for (PerfilType tipo : PerfilType.values()) {
//                    PerfilEntity perfilEntity = new PerfilEntity();
//                    perfilEntity.setTipo(tipo);
//
//                    // Obter permissões para o perfil
//                    Set<PermissaoType> permissoes = PerfilType.getPermissoesParaPerfil(tipo);
//
//                    // Converter PermissaoType para PermissaoEntity usando o adaptador
//                    Set<PermissaoEntity> permissoesEntities = permissoes.stream()
//                            .map(permissaoType -> permissaoRepository.findByCodigo(permissaoType.getCodigo())
//                                    .map(permissaoAdapter::toEntity)  // Converter PermissaoType para PermissaoEntity
//                                    .orElseThrow(() -> new IllegalStateException("Permissão não encontrada: " + permissaoType.getCodigo())))
//                            .collect(Collectors.toSet());
//
//                    // Atribuir as permissões ao perfil
//                    perfilEntity.setPermissoes(permissoesEntities);
//
//                    // Salvar o perfil
//                    perfilRepository.save(perfilEntity.getTipo());
//                }
//
//            }
//            // Log de sucesso
//            logger.info("DataLoader executado com sucesso: Perfis e permissões inseridos.");
//        };
//    };
//}
//
//
