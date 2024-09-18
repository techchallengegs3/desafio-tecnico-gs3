package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.PermissaoType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PermissaoEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PermissaoAdapterImplTest {

    private PermissaoAdapterImpl permissaoAdapter;

    @BeforeEach
    void setUp() {
        permissaoAdapter = new PermissaoAdapterImpl();
    }

    @Test
    void deveConverterPermissaoTypeParaPermissaoEntity() {
        PermissaoType permissaoType = PermissaoType.CRIAR_USUARIOS;
        PermissaoEntity permissaoEntity = permissaoAdapter.toEntity(permissaoType);

        assertNotNull(permissaoEntity);
        assertEquals(PermissaoType.CRIAR_USUARIOS, permissaoEntity.getDescricao());
    }

    @Test
    void deveRetornarNuloQuandoConverterPermissaoTypeNuloParaPermissaoEntity() {
        PermissaoEntity permissaoEntity = permissaoAdapter.toEntity(null);
        assertNull(permissaoEntity);
    }

    @Test
    void deveConverterPermissaoEntityParaPermissaoType() {
        PermissaoEntity permissaoEntity = PermissaoEntity.builder()
                .descricao(PermissaoType.ATRIBUIR_PERFIS)
                .build();

        PermissaoType permissaoType = permissaoAdapter.toDomain(permissaoEntity);
        assertNotNull(permissaoType);
        assertEquals(PermissaoType.ATRIBUIR_PERFIS, permissaoType);
    }

    @Test
    void deveRetornarNuloQuandoConverterPermissaoEntityNuloParaPermissaoType() {
        PermissaoType permissaoType = permissaoAdapter.toDomain(null);
        assertNull(permissaoType);
    }

    @Test
    void deveConverterSetDePermissaoTypeParaSetDePermissaoEntity() {
        Set<PermissaoType> permissoes = new HashSet<>();
        permissoes.add(PermissaoType.CRIAR_USUARIOS);
        permissoes.add(PermissaoType.MODIFICAR_PERFIS_EXISTENTES);

        Set<PermissaoEntity> permissoesEntity = permissaoAdapter.toEntitySet(permissoes);

        assertNotNull(permissoesEntity);
        assertEquals(2, permissoesEntity.size());
    }

    @Test
    void deveConverterSetDePermissaoEntityParaSetDePermissaoType() {
        Set<PermissaoEntity> permissoesEntity = new HashSet<>();
        permissoesEntity.add(PermissaoEntity.builder().descricao(PermissaoType.VISUALIZAR_INFORMACOES).build());
        permissoesEntity.add(PermissaoEntity.builder().descricao(PermissaoType.ALTERAR_INFORMACOES).build());

        Set<PermissaoType> permissoesType = permissaoAdapter.toDomainSet(permissoesEntity);

        assertNotNull(permissoesType);
        assertEquals(2, permissoesType.size());
    }

    @Test
    void deveRetornarSetVazioQuandoConverterSetVazioDePermissaoTypeParaPermissaoEntity() {
        Set<PermissaoEntity> permissoesEntity = permissaoAdapter.toEntitySet(new HashSet<>());
        assertNotNull(permissoesEntity);
        assertTrue(permissoesEntity.isEmpty());
    }

    @Test
    void deveRetornarSetVazioQuandoConverterSetVazioDePermissaoEntityParaPermissaoType() {
        Set<PermissaoType> permissoesType = permissaoAdapter.toDomainSet(new HashSet<>());
        assertNotNull(permissoesType);
        assertTrue(permissoesType.isEmpty());
    }
}

