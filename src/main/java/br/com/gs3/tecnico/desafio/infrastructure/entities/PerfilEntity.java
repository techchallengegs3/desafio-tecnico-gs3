package br.com.gs3.tecnico.desafio.infrastructure.entities;

import br.com.gs3.tecnico.desafio.domain.entities.PerfilType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "perfis")
public class PerfilEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PerfilType tipo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "perfil_permissao",
            joinColumns = @JoinColumn(name = "perfil_id"),
            inverseJoinColumns = @JoinColumn(name = "permissao_id")
    )
    private Set<PermissaoEntity> permissoes;
}
