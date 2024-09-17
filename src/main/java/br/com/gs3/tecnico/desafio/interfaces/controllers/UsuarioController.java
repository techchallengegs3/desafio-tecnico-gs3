package br.com.gs3.tecnico.desafio.interfaces.controllers;

import br.com.gs3.tecnico.desafio.domain.entities.Usuario;
import br.com.gs3.tecnico.desafio.interfaces.dtos.UsuarioDTO;
import br.com.gs3.tecnico.desafio.usecases.CriarUsuarioUseCase;
import br.com.gs3.tecnico.desafio.usecases.DeletarUsuarioUseCase;
import br.com.gs3.tecnico.desafio.usecases.ListarUsuarioUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/usuarios")
@Tag(name = "Usuários", description = "API de Gestão de Usuários")
public class UsuarioController {

    private final CriarUsuarioUseCase criarUsuarioUseCase;
    private final DeletarUsuarioUseCase deletarUsuarioUseCase;
    private final ListarUsuarioUseCase listarUsuarioUseCase;

    public UsuarioController(CriarUsuarioUseCase criarUsuarioUseCase, DeletarUsuarioUseCase deletarUsuarioUseCase, ListarUsuarioUseCase listarUsuarioUseCase) {
        this.criarUsuarioUseCase = criarUsuarioUseCase;
        this.deletarUsuarioUseCase = deletarUsuarioUseCase;
        this.listarUsuarioUseCase = listarUsuarioUseCase;
    }

    @Operation(summary = "Cria um novo usuário", description = "Permite a criação de um novo usuário com nome, e-mail, senha, endereço e telefone.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(
            @Parameter(description = "Dados do usuário para criação", required = true)
            @RequestBody UsuarioDTO usuarioDTO) {

        Usuario usuario = criarUsuarioUseCase.execute(
                usuarioDTO.getNome(),
                usuarioDTO.getEmail(),
                usuarioDTO.getSenha(),
                usuarioDTO.getEndereco(),
                usuarioDTO.getTelefone(),
                usuarioDTO.getPerfil()
        );

        return ResponseEntity.ok(usuario);
    }


    @Operation(summary = "Deleta um usuário", description = "Permite a deleção de um usuário pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(
            @Parameter(description = "ID do usuário a ser deletado", required = true)
            @PathVariable("id") Long id) {

        try {
            deletarUsuarioUseCase.execute(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Lista todos os usuários", description = "Recupera todos os usuários cadastrados no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = listarUsuarioUseCase.execute();
        return ResponseEntity.ok(usuarios);
    }
}
