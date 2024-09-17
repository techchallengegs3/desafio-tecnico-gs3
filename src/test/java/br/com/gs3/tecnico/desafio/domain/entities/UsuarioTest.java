package br.com.gs3.tecnico.desafio.domain.entities;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UsuarioTest {

    @Test
    public void deveRetornarUsuarioComConstrutorPadrao() {
        Usuario usuario = new Usuario();

        assertThat(usuario.getId()).isNull();
        assertThat(usuario.getNome()).isNull();
        assertThat(usuario.getEmail()).isNull();
        assertThat(usuario.getSenha()).isNull();
        assertThat(usuario.getEndereco()).isNull();
        assertThat(usuario.getTelefone()).isNull();
        assertThat(usuario.getPerfil()).isNull();
    }

    @Test
    public void deveAlterarSenhaUsuario() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nome("Teste Usuário")
                .email("teste@email.com")
                .senha("senha123")
                .build();

        usuario.setSenha("novaSenha123");
        assertThat(usuario.getSenha()).isEqualTo("novaSenha123");
    }

    @Test
    public void deveAtualizarEmailUsuario() {
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nome("Teste Usuário")
                .email("teste@email.com")
                .senha("senha123")
                .build();

        usuario.setEmail("novoemail@email.com");
        assertThat(usuario.getEmail()).isEqualTo("novoemail@email.com");
    }

    @Test
    public void deveTestarIgualdadeEntreUsuarios() {
        Perfil perfil = Perfil.builder().id(1L).tipo(PerfilType.USUARIO_COMUM).build();
        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .perfil(perfil)
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .perfil(perfil)
                .build();


        assertThat(usuario1).isEqualTo(usuario2);
        assertThat(usuario1.hashCode()).isEqualTo(usuario2.hashCode());
    }

    @Test
    public void deveTestarUsuariosDiferentes() {
        Perfil perfil1 = Perfil.builder().id(1L).tipo(PerfilType.USUARIO_COMUM).build();
        Perfil perfil2 = Perfil.builder().id(2L).tipo(PerfilType.ADMINISTRADOR).build();

        Usuario usuario1 = Usuario.builder()
                .id(1L)
                .nome("João")
                .email("joao@email.com")
                .perfil(perfil1)
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(2L)
                .nome("Maria")
                .email("maria@email.com")
                .perfil(perfil2)
                .build();

        assertThat(usuario1).isNotEqualTo(usuario2);
        assertThat(usuario1.hashCode()).isNotEqualTo(usuario2.hashCode());
    }

    @Test
    public void deveGerarExcecaoQuandoEmailInvalido() {
        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> usuario.setEmail(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email não pode ser nulo ou vazio");

        assertThatThrownBy(() -> usuario.setEmail(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email não pode ser nulo ou vazio");
    }

    @Test
    public void deveGerarExcecaoQuandoSenhaInvalida() {
        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> usuario.setSenha(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Senha não pode ser nula ou vazia");

        assertThatThrownBy(() -> usuario.setSenha(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Senha não pode ser nula ou vazia");
    }

    @Test
    public void deveGerarExcecaoQuandoNomeInvalido() {
        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> usuario.setNome(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome não pode ser nulo ou vazio");

        assertThatThrownBy(() -> usuario.setNome(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome não pode ser nulo ou vazio");
    }

    @Test
    public void deveGerarExcecaoQuandoEnderecoInvalido() {
        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> usuario.setEndereco(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Endereco não pode ser nulo ou vazio");

        assertThatThrownBy(() -> usuario.setEndereco(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Endereco não pode ser nulo ou vazio");
    }

    @Test
    public void deveGerarExcecaoQuandoTelefoneInvalido() {
        Usuario usuario = new Usuario();

        assertThatThrownBy(() -> usuario.setTelefone(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Telefone não pode ser nulo ou vazio");

        assertThatThrownBy(() -> usuario.setTelefone(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Telefone não pode ser nulo ou vazio");
    }

    @Test
    public void deveManterReferenciasDeObjetosCorretamente() {
        Perfil perfil = Perfil.builder().id(1L).tipo(PerfilType.USUARIO_COMUM).build();
        Usuario usuario = Usuario.builder()
                .id(1L)
                .nome("Carlos")
                .perfil(perfil)
                .build();

        Perfil novoPerfil = Perfil.builder().id(2L).tipo(PerfilType.ADMINISTRADOR).build();
        usuario.setPerfil(novoPerfil);

        assertThat(usuario.getPerfil()).isEqualTo(novoPerfil);
        assertThat(usuario.getPerfil().getTipo()).isEqualTo(PerfilType.ADMINISTRADOR);
    }

    @Test
    public void deveAtualizarDadosUsuario() {
        Usuario usuario = new Usuario();

        usuario.setId(3L);
        usuario.setNome("Carlos Silva");
        usuario.setEmail("carlos@email.com");
        usuario.setSenha("senha789");
        usuario.setEndereco("Rua C, 789");
        usuario.setTelefone("777777777");

        assertThat(usuario.getId()).isEqualTo(3L);
        assertThat(usuario.getNome()).isEqualTo("Carlos Silva");
        assertThat(usuario.getEmail()).isEqualTo("carlos@email.com");
        assertThat(usuario.getSenha()).isEqualTo("senha789");
        assertThat(usuario.getEndereco()).isEqualTo("Rua C, 789");
        assertThat(usuario.getTelefone()).isEqualTo("777777777");
    }

    @Test
    public void deveAtualizarPerfilDoUsuario() {
        Usuario usuario = Usuario.builder().build();
        Perfil perfilAntigo = Perfil.builder().id(1L).tipo(PerfilType.USUARIO_COMUM).build();
        Perfil perfilNovo = Perfil.builder().id(2L).tipo(PerfilType.ADMINISTRADOR).build();

        usuario.setPerfil(perfilAntigo);
        assertThat(usuario.getPerfil()).isEqualTo(perfilAntigo);

        usuario.setPerfil(perfilNovo);
        assertThat(usuario.getPerfil()).isEqualTo(perfilNovo);
    }
}

