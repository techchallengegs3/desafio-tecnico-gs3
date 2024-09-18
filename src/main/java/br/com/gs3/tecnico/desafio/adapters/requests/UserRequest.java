package br.com.gs3.tecnico.desafio.adapters.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "O endereço é obrigatória")
    private String endereco;

    @NotBlank(message = "O telefone é obrigatória")
    private String telefone;
}

