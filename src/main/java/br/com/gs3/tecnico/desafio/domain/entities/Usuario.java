package br.com.gs3.tecnico.desafio.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String telefone;
    private Perfil perfil;

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email não pode ser nulo ou vazio");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email inválido");
        }
        this.email = email;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) {
            throw new IllegalArgumentException("Senha não pode ser nula ou vazia");
        }
        if (senha.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 6 caracteres");
        }
        this.senha = senha;
    }
}

