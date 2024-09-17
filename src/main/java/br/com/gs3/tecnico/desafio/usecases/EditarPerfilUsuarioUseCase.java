package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Perfil;
import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class EditarPerfilUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public EditarPerfilUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario editarPerfil(Long idUsuario, Perfil novoPerfil) {
        // Validação de idUsuario nulo
        if (idUsuario == null) {
            throw new IllegalArgumentException("ID do usuário não pode ser nulo");
        }

        if (novoPerfil == null) {
            throw new IllegalArgumentException("Perfil não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setPerfil(novoPerfil);
        return usuarioRepository.save(usuario);
    }
}
