package br.com.gs3.tecnico.desafio.infrastructure.adapters;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.infrastructure.entities.PerfilEntity;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

class PerfilAdapterImplTest {

    private final PerfilAdapter perfilAdapter = new PerfilAdapterImpl();

    @Test
    void deveConverterPerfilTypeParaPerfilEntity() {
        PerfilType perfilType = PerfilType.ADMINISTRADOR;

        PerfilEntity perfilEntity = perfilAdapter.toEntity(perfilType);

        assertNotNull(perfilEntity);
        assertEquals(perfilType, perfilEntity.getTipo());
    }

    @Test
    void deveRetornarNullQuandoConverterPerfilTypeParaPerfilEntityDadoNull() {
        PerfilEntity perfilEntity = perfilAdapter.toEntity(null);
        assertNull(perfilEntity);
    }

    @Test
    void deveConverterPerfilEntityParaPerfilType() {
        PerfilEntity perfilEntity = PerfilEntity.builder()
                .tipo(PerfilType.USUARIO_COMUM)
                .build();

        PerfilType perfilType = perfilAdapter.toDomain(perfilEntity);

        assertNotNull(perfilType);
        assertEquals(perfilEntity.getTipo(), perfilType);
    }

    @Test
    void deveRetornarNullQuandoConverterPerfilEntityParaPerfilTypeDadoNull() {
        PerfilType perfilType = perfilAdapter.toDomain(null);
        assertNull(perfilType);
    }

    @Test
    void deveConverterConjuntoPerfilTypeParaConjuntoPerfilEntity() {
        Set<PerfilType> perfilTypes = new HashSet<>();
        perfilTypes.add(PerfilType.ADMINISTRADOR);
        perfilTypes.add(PerfilType.USUARIO_COMUM);

        Set<PerfilEntity> perfilEntities = perfilAdapter.toEntitySet(perfilTypes);

        assertNotNull(perfilEntities);
        assertEquals(perfilTypes.size(), perfilEntities.size());
        assertTrue(perfilEntities.stream().anyMatch(pe -> pe.getTipo() == PerfilType.ADMINISTRADOR));
        assertTrue(perfilEntities.stream().anyMatch(pe -> pe.getTipo() == PerfilType.USUARIO_COMUM));
    }

    @Test
    void deveRetornarNullQuandoConverterConjuntoPerfilTypeParaConjuntoPerfilEntityDadoNull() {
        Set<PerfilEntity> perfilEntities = perfilAdapter.toEntitySet(null);
        assertNull(perfilEntities);
    }

    @Test
    void deveConverterConjuntoPerfilEntityParaConjuntoPerfilType() {
        Set<PerfilEntity> perfilEntities = new HashSet<>();
        perfilEntities.add(PerfilEntity.builder().tipo(PerfilType.ADMINISTRADOR).build());
        perfilEntities.add(PerfilEntity.builder().tipo(PerfilType.USUARIO_COMUM).build());

        Set<PerfilType> perfilTypes = perfilAdapter.toDomainSet(perfilEntities);

        assertNotNull(perfilTypes);
        assertEquals(perfilEntities.size(), perfilTypes.size());
        assertTrue(perfilTypes.contains(PerfilType.ADMINISTRADOR));
        assertTrue(perfilTypes.contains(PerfilType.USUARIO_COMUM));
    }

    @Test
    void deveRetornarNullQuandoConverterConjuntoPerfilEntityParaConjuntoPerfilTypeDadoNull() {
        Set<PerfilType> perfilTypes = perfilAdapter.toDomainSet(null);
        assertNull(perfilTypes);
    }
}
