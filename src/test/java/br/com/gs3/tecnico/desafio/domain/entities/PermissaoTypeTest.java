package br.com.gs3.tecnico.desafio.domain.entities;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PermissaoTypeTest {

    @Test
    public void deveRetornarPermissaoPeloCodigo() {
        PermissaoType permissao = PermissaoType.fromCodigo(1L);
        assertThat(permissao).isEqualTo(PermissaoType.CRIAR_USUARIOS);
    }

    @Test
    public void deveLancarExcecaoParaCodigoInvalido() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PermissaoType.fromCodigo(99L)
        );
        assertThat(exception.getMessage()).isEqualTo("Código não encontrado: 99");
    }

    @Test
    public void deveRetornarCodigoCorretoParaCadaPermissao() {
        assertThat(PermissaoType.CRIAR_USUARIOS.getCodigo()).isEqualTo(1L);
        assertThat(PermissaoType.ATRIBUIR_PERFIS.getCodigo()).isEqualTo(2L);
        assertThat(PermissaoType.MODIFICAR_PERFIS_EXISTENTES.getCodigo()).isEqualTo(3L);
        assertThat(PermissaoType.VISUALIZAR_INFORMACOES.getCodigo()).isEqualTo(4L);
        assertThat(PermissaoType.ALTERAR_INFORMACOES.getCodigo()).isEqualTo(5L);
    }

    @Test
    public void deveRetornarDescricaoCorretaParaCadaPermissao() {
        assertThat(PermissaoType.CRIAR_USUARIOS.getDescricao()).isEqualTo("CRIAR_USUARIOS");
        assertThat(PermissaoType.ATRIBUIR_PERFIS.getDescricao()).isEqualTo("ATRIBUIR_PERFIS");
        assertThat(PermissaoType.MODIFICAR_PERFIS_EXISTENTES.getDescricao()).isEqualTo("MODIFICAR_PERFIS_EXISTENTES");
        assertThat(PermissaoType.VISUALIZAR_INFORMACOES.getDescricao()).isEqualTo("VISUALIZAR_INFORMACOES");
        assertThat(PermissaoType.ALTERAR_INFORMACOES.getDescricao()).isEqualTo("ALTERAR_INFORMACOES");
    }
}

