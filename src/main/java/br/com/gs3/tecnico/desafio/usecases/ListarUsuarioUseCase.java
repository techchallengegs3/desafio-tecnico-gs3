package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public ListarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> execute() {
        return usuarioRepository.findAll();
    }
}
