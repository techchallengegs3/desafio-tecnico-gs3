package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.Perfil;
import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.PerfilAdapter;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PerfilAdapter perfilAdapter;

    public CriarUsuarioUseCase(UsuarioRepository usuarioRepository, PerfilAdapter perfilAdapter) {
        this.usuarioRepository = usuarioRepository;
        this.perfilAdapter = perfilAdapter;
    }

    public Usuario execute(String nome, String email, String senha, String endereco, String telefone, PerfilType perfilType) {

        validarDados(nome, email, senha);

        Usuario usuario = Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .endereco(endereco)
                .telefone(telefone)
                .perfil(perfilAdapter.toDomainFromType(perfilType))
                .build();

        return usuarioRepository.save(usuario);
    }

    private void validarDados(String nome, String email, String senha) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido");
        }
        if (senha == null) {
            throw new IllegalArgumentException("Senha é obrigatória");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres");
        }
    }
}
