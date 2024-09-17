package br.com.gs3.tecnico.desafio.domain.entities;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class PerfilTest {

    @Test
    public void deveCriarPerfilComSucessoUsandoBuilder() {
        Set<PermissaoType> permissoes = Set.of(PermissaoType.CRIAR_USUARIOS, PermissaoType.ATRIBUIR_PERFIS);
        PerfilType perfilType = PerfilType.ADMINISTRADOR;
        Long id = 1L;

        Perfil perfil = Perfil.builder()
                .id(id)
                .tipo(perfilType)
                .permissoes(permissoes)
                .build();

        assertThat(perfil.getId()).isEqualTo(id);
        assertThat(perfil.getTipo()).isEqualTo(perfilType);
        assertThat(perfil.getPermissoes()).containsExactlyInAnyOrder(PermissaoType.CRIAR_USUARIOS, PermissaoType.ATRIBUIR_PERFIS);
    }

    @Test
    public void deveAtualizarPermissoesDoPerfil() {
        Perfil perfil = Perfil.builder().build();
        Set<PermissaoType> novasPermissoes = Set.of(PermissaoType.ALTERAR_INFORMACOES);

        perfil.setPermissoes(novasPermissoes);
        assertThat(perfil.getPermissoes()).containsExactly(PermissaoType.ALTERAR_INFORMACOES);
    }

    @Test
    public void deveAlterarTipoDoPerfil() {
        Perfil perfil = Perfil.builder().build();
        PerfilType novoTipo = PerfilType.USUARIO_COMUM;

        perfil.setTipo(novoTipo);
        assertThat(perfil.getTipo()).isEqualTo(novoTipo);
    }

    @Test
    public void deveVerificarConstrutorCompleto() {
        Long id = 10L;
        PerfilType tipo = PerfilType.ADMINISTRADOR;
        Set<PermissaoType> permissoes = Set.of(PermissaoType.MODIFICAR_PERFIS_EXISTENTES);

        Perfil perfil = new Perfil(id, tipo, permissoes);

        assertThat(perfil.getId()).isEqualTo(id);
        assertThat(perfil.getTipo()).isEqualTo(tipo);
        assertThat(perfil.getPermissoes()).containsExactly(PermissaoType.MODIFICAR_PERFIS_EXISTENTES);
    }

    @Test
    public void deveVerificarConstrutorSemParametros() {
        Perfil perfil = new Perfil();

        assertThat(perfil.getId()).isNull();
        assertThat(perfil.getTipo()).isNull();
        assertThat(perfil.getPermissoes()).isNull();
    }
}

