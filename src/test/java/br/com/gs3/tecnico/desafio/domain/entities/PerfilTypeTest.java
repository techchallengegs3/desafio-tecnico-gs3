package br.com.gs3.tecnico.desafio.domain.entities;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PerfilTypeTest {

    @Test
    public void deveRetornarPerfilPelaDescricao() {
        PerfilType perfil = PerfilType.fromDescricao("Administrador");
        assertThat(perfil).isEqualTo(PerfilType.ADMINISTRADOR);
    }

    @Test
    public void deveLancarExcecaoParaDescricaoInvalida() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                PerfilType.fromDescricao("Perfil Inexistente")
        );
        assertThat(exception.getMessage()).isEqualTo("Descrição de perfil inválida: Perfil Inexistente");
    }

    @Test
    public void deveRetornarPermissoesParaAdministrador() {
        Set<PermissaoType> permissoes = PerfilType.getPermissoesParaPerfil(PerfilType.ADMINISTRADOR);
        assertThat(permissoes)
                .containsExactlyInAnyOrder(
                        PermissaoType.CRIAR_USUARIOS,
                        PermissaoType.ATRIBUIR_PERFIS,
                        PermissaoType.MODIFICAR_PERFIS_EXISTENTES
                );
    }

    @Test
    public void deveRetornarPermissoesParaUsuarioComum() {
        Set<PermissaoType> permissoes = PerfilType.getPermissoesParaPerfil(PerfilType.USUARIO_COMUM);
        assertThat(permissoes)
                .containsExactlyInAnyOrder(
                        PermissaoType.VISUALIZAR_INFORMACOES,
                        PermissaoType.ALTERAR_INFORMACOES
                );
    }

    @Test
    public void deveRetornarDescricaoCorretaParaCadaPerfil() {
        assertThat(PerfilType.ADMINISTRADOR.getDescricao()).isEqualTo("Administrador");
        assertThat(PerfilType.USUARIO_COMUM.getDescricao()).isEqualTo("Usuário Comum");
    }
}

