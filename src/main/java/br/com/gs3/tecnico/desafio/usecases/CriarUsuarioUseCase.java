package br.com.gs3.tecnico.desafio.usecases;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.domain.repositories.UsuarioRepository;
import br.com.gs3.tecnico.desafio.infrastructure.adapters.PerfilAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CriarUsuarioUseCase {

    private static final Logger logger = LoggerFactory.getLogger(CriarUsuarioUseCase.class);
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

        logger.info("usuario.getNome(): " + usuario.getNome());
        logger.info("usuario.getEmail(): " + usuario.getEmail());
        logger.info("usuario.getSenha(): " + usuario.getSenha());
        logger.info("usuario.getEndereco(): " + usuario.getEndereco());
        logger.info("usuario.getTelefone(): " + usuario.getTelefone());
        logger.info("usuario.getPerfil() - id: " + usuario.getPerfil().getId());
        logger.info("usuario.getPerfil() - tipo: " + usuario.getPerfil().getTipo());
//        logger.info("usuario.getPerfil() - usuarios: " + usuario.getPerfil().getUsuarios().size());
//        logger.info("usuario.getPerfil() - permissoes: " + usuario.getPerfil().getPermissoes().size());

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
