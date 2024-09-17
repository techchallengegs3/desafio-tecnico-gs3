package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditarUsuarioInformacoesUseCase {

    private final UsuarioRepository usuarioRepository;

    public Usuario editarInformacoes(Long idUsuario, String endereco, String telefone) {
        if (endereco == null && telefone == null) {
            throw new IllegalArgumentException("Endereço e telefone não podem ser nulos");
        }

        if (endereco != null && endereco.trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço não pode ser vazio");
        }

        if (telefone != null && telefone.trim().isEmpty()) {
            throw new IllegalArgumentException("Telefone não pode ser vazio");
        }

        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (endereco != null) {
            usuario.setEndereco(endereco);
        }
        if (telefone != null) {
            usuario.setTelefone(telefone);
        }

        return usuarioRepository.save(usuario);
    }
}
