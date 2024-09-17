package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissaoAdapterImplTest {

    private final PermissaoAdapterImpl adapter = new PermissaoAdapterImpl();

    @Test
    void deveConverterPermissaoTypeParaPermissaoEntity() {
        PermissaoType permissaoType = PermissaoType.CRIAR_USUARIOS;
        PermissaoEntity permissaoEntity = adapter.toEntity(permissaoType);

        assertEquals(permissaoType.getCodigo(), permissaoEntity.getCodigo());
        assertEquals(permissaoType.getDescricao(), permissaoEntity.getDescricao());
    }

    @Test
    void deveConverterPermissaoEntityParaPermissaoType() {
        PermissaoEntity permissaoEntity = PermissaoEntity.builder()
                .codigo(PermissaoType.CRIAR_USUARIOS.getCodigo())
                .descricao(PermissaoType.CRIAR_USUARIOS.getDescricao())
                .build();

        PermissaoType permissaoType = adapter.toDomain(permissaoEntity);

        assertEquals(PermissaoType.CRIAR_USUARIOS, permissaoType);
    }

    @Test
    void deveConverterSetDePermissaoTypeParaSetDePermissaoEntity() {
        Set<PermissaoType> permissoesType = Set.of(PermissaoType.CRIAR_USUARIOS, PermissaoType.VISUALIZAR_INFORMACOES);

        Set<PermissaoEntity> permissoesEntity = adapter.toEntitySet(permissoesType);

        assertEquals(permissoesType.size(), permissoesEntity.size());
        for (PermissaoType permissaoType : permissoesType) {
            PermissaoEntity permissaoEntity = permissoesEntity.stream()
                    .filter(pe -> pe.getCodigo().equals(permissaoType.getCodigo()))
                    .findFirst()
                    .orElse(null);
            assertNotNull(permissaoEntity);
            assertEquals(permissaoType.getDescricao(), permissaoEntity.getDescricao());
        }
    }

    @Test
    void deveConverterSetDePermissaoEntityParaSetDePermissaoType() {
        Set<PermissaoEntity> permissoesEntity = Set.of(
                PermissaoEntity.builder()
                        .codigo(PermissaoType.CRIAR_USUARIOS.getCodigo())
                        .descricao(PermissaoType.CRIAR_USUARIOS.getDescricao())
                        .build(),
                PermissaoEntity.builder()
                        .codigo(PermissaoType.VISUALIZAR_INFORMACOES.getCodigo())
                        .descricao(PermissaoType.VISUALIZAR_INFORMACOES.getDescricao())
                        .build()
        );

        Set<PermissaoType> permissoesType = adapter.toDomainSet(permissoesEntity);

        assertEquals(permissoesEntity.size(), permissoesType.size());
        for (PermissaoEntity permissaoEntity : permissoesEntity) {
            PermissaoType permissaoType = permissoesType.stream()
                    .filter(pt -> pt.getCodigo().equals(permissaoEntity.getCodigo()))
                    .findFirst()
                    .orElse(null);
            assertNotNull(permissaoType);
            assertEquals(permissaoEntity.getDescricao(), permissaoType.getDescricao());
        }
    }

    private void assertNotNull(PermissaoType permissaoType) {
        if (permissaoType == null) {
            throw new AssertionError("PermissãoType não deve ser nulo");
        }
    }

    private void assertNotNull(PermissaoEntity permissaoEntity) {
        if (permissaoEntity == null) {
            throw new AssertionError("PermissãoEntity não deve ser nulo");
        }
    }
}
