package br.com.gs3.tecnico.desafio.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Perfil {
    private Long id;
    private PerfilType tipo;
    private Set<PermissaoType> permissoes;
}