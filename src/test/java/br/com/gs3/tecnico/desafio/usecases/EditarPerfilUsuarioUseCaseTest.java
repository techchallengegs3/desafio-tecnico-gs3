package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Perfil;
import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EditarPerfilUsuarioUseCaseTest {

    @InjectMocks
    private EditarPerfilUsuarioUseCase editarPerfilUsuarioUseCase;

    @Mock
    private UsuarioRepository usuarioRepository;

    private Usuario usuarioMock;
    private Perfil perfilMock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        perfilMock = Perfil.builder()
                .id(1L)
                .tipo(PerfilType.ADMINISTRADOR)
                .build();

        usuarioMock = Usuario.builder()
                .id(1L)
                .nome("João Silva")
                .email("joao@email.com")
                .perfil(perfilMock)
                .build();
    }

    @Test
    public void deveEditarPerfilComSucesso() {
        Perfil novoPerfil = Perfil.builder()
                .id(2L)
                .tipo(PerfilType.USUARIO_COMUM)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, novoPerfil);

        assertThat(usuarioEditado).isNotNull();
        assertThat(usuarioEditado.getPerfil().getTipo()).isEqualTo(PerfilType.USUARIO_COMUM);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    public void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> editarPerfilUsuarioUseCase.editarPerfil(1L, perfilMock))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Usuário não encontrado");
    }

    @Test
    public void deveLancarExcecaoQuandoPerfilForNulo() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));

        assertThatThrownBy(() -> editarPerfilUsuarioUseCase.editarPerfil(1L, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Perfil não pode ser nulo");
    }

    @Test
    public void deveManterOMesmoPerfilQuandoPerfilNaoAlterado() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, perfilMock);

        assertThat(usuarioEditado.getPerfil()).isEqualTo(perfilMock);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    public void deveAtualizarOMesmoUsuario() {
        Perfil novoPerfil = Perfil.builder()
                .id(2L)
                .tipo(PerfilType.ADMINISTRADOR)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, novoPerfil);

        assertThat(usuarioEditado.getId()).isEqualTo(usuarioMock.getId());
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    public void deveSalvarUsuarioComNovoPerfil() {
        Perfil novoPerfil = Perfil.builder()
                .id(3L)
                .tipo(PerfilType.USUARIO_COMUM)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, novoPerfil);

        verify(usuarioRepository, times(1)).save(usuarioMock);
        assertThat(usuarioEditado.getPerfil()).isEqualTo(novoPerfil);
    }

    @Test
    public void naoDeveSalvarUsuarioQuandoNaoExistirMudancaDePerfil() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, perfilMock);

        verify(usuarioRepository, times(1)).save(usuarioMock);
        assertThat(usuarioEditado.getPerfil()).isEqualTo(perfilMock);
    }

    @Test
    public void deveLancarExcecaoQuandoIdUsuarioForNulo() {
        assertThatThrownBy(() -> editarPerfilUsuarioUseCase.editarPerfil(null, perfilMock))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID do usuário não pode ser nulo");
    }

    @Test
    public void deveSalvarUsuarioQuandoPerfilAlteradoDeAdminParaUsuarioComum() {
        Perfil novoPerfil = Perfil.builder()
                .id(2L)
                .tipo(PerfilType.USUARIO_COMUM)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, novoPerfil);

        assertThat(usuarioEditado.getPerfil().getTipo()).isEqualTo(PerfilType.USUARIO_COMUM);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }

    @Test
    public void deveSalvarUsuarioQuandoPerfilAlteradoDeUsuarioComumParaAdmin() {
        Perfil perfilUsuarioComum = Perfil.builder()
                .id(2L)
                .tipo(PerfilType.USUARIO_COMUM)
                .build();

        usuarioMock.setPerfil(perfilUsuarioComum);

        Perfil novoPerfilAdmin = Perfil.builder()
                .id(1L)
                .tipo(PerfilType.ADMINISTRADOR)
                .build();

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioMock));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioMock);

        Usuario usuarioEditado = editarPerfilUsuarioUseCase.editarPerfil(1L, novoPerfilAdmin);

        assertThat(usuarioEditado.getPerfil().getTipo()).isEqualTo(PerfilType.ADMINISTRADOR);
        verify(usuarioRepository, times(1)).save(usuarioMock);
    }
}
