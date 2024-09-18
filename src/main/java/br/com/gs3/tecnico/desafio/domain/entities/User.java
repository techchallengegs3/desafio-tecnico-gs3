package br.com.gs3.tecnico.desafio.domain.entities;

import br.com.gs3.tecnico.desafio.domain.exceptions.BusinessException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="`User`")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    @Column(name="nome", nullable=false, length=100)
    private String nome;
    @Column(name="email", nullable=false, length=30)
    private String email;
    @Column(name="endereco", nullable=false, length=100)
    private String endereco;
    @Column(name="telefone", nullable=false, length=100)
    private String telefone;

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new BusinessException("Nome não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new BusinessException("Email não pode ser nulo ou vazio");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new BusinessException("Email inválido");
        }
        this.email = email;
    }

    public void setEndereco(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new BusinessException("Endereco não pode ser nulo ou vazio");
        }
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new BusinessException("Telefone não pode ser nulo ou vazio");
        }
        this.telefone = telefone;
    }
}
