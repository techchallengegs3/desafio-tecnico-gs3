package br.com.gs3.tecnico.desafio.interfaces.dtos;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Representa um usuário")
public class UsuarioDTO {

    @Schema(description = "Nome do usuário", example = "João Silva")
    private String nome;

    @Schema(description = "E-mail do usuário", example = "joao.silva@example.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "123456")
    private String senha;

    @Schema(description = "Endereco do usuário", example = "SQS 214 Bloco A Apt. 101")
    private String endereco;

    @Schema(description = "Telefone do usuário", example = "(61) 98123-4567")
    private String telefone;

    @Schema(description = "Perfil do usuário", example = "ADMINISTRADOR")
    private PerfilType perfil;
}