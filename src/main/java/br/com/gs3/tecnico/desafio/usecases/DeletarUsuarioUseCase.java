package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeletarUsuarioUseCase {

    private final UsuarioRepository usuarioRepository;

    public DeletarUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void execute(Long userId) {
        if (usuarioRepository.existsById(userId)) {
            usuarioRepository.delete(userId);
        } else {
            throw new IllegalArgumentException("Usuário com ID " + userId + " não encontrado.");
        }
    }
}
