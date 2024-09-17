package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.PerfilAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CriarUsuarioUseCaseTest {

    @InjectMocks
    private CriarUsuarioUseCase criarUsuarioUseCase;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PerfilAdapter perfilAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
    public void deveCriarUsuarioComSucesso() {
        // Mock de um perfil
        PerfilType perfilMock = PerfilType.ADMINISTRADOR;

        // Mock de um usuário com o novo atributo perfil
        Usuario usuarioMock = Usuario.builder()
                .nome("João Silva")
                .email("joao@email.com")
                .senha("senha123")
                .endereco("Rua Principal, 123")
                .telefone("999999999")
                .perfil(perfilAdapter.toDomainFromType(perfilMock))
                .build();

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioCriado = criarUsuarioUseCase.execute(
                "João Silva", "joao@email.com", "senha123", "Rua Principal, 123", "999999999", perfilMock
        );

        assertThat(usuarioCriado).isNotNull();
        assertThat(usuarioCriado.getNome()).isEqualTo("João Silva");
        assertThat(usuarioCriado.getEmail()).isEqualTo("joao@email.com");
        assertThat(usuarioCriado.getSenha()).isEqualTo("senha123");
        assertThat(usuarioCriado.getEndereco()).isEqualTo("Rua Principal, 123");
        assertThat(usuarioCriado.getTelefone()).isEqualTo("999999999");
        assertThat(usuarioCriado.getPerfil()).isEqualTo(perfilMock);
    }

    @Test
    public void deveLancarExcecaoQuandoNomeForInvalido() {
        assertThatThrownBy(() -> criarUsuarioUseCase.execute(
                "", "joao@email.com", "senha123", "Rua Principal, 123", "999999999", PerfilType.ADMINISTRADOR
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Nome é obrigatório");
    }

    @Test
    public void deveLancarExcecaoQuandoEmailForInvalido() {
        assertThatThrownBy(() -> criarUsuarioUseCase.execute(
                "João Silva", "email_invalido", "senha123", "Rua Principal, 123", "999999999", PerfilType.ADMINISTRADOR
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("E-mail inválido");
    }

    @Test
    public void deveLancarExcecaoQuandoSenhaForCurta() {
        assertThatThrownBy(() -> criarUsuarioUseCase.execute(
                "João Silva", "joao@email.com", "123", "Rua Principal, 123", "999999999", PerfilType.ADMINISTRADOR
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("A senha deve ter no mínimo 6 caracteres");
    }

    @Test
    public void deveLancarExcecaoQuandoSenhaNaoForInformada() {
        assertThatThrownBy(() -> criarUsuarioUseCase.execute(
                "João Silva", "joao@email.com", null, "Rua Principal, 123", "999999999", PerfilType.ADMINISTRADOR
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Senha é obrigatória");
    }
}
