package br.com.gs3.tecnico.desafio.domain.entities;

import java.util.Map;
import java.util.Set;

public enum PerfilType {
    ADMINISTRADOR(1L, "Administrador"),
    USUARIO_COMUM(2L, "Usuário Comum");

    private final String descricao;
    private final Long id;

    PerfilType(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getId() {
        return id;
    }

    public static PerfilType fromDescricao(String descricao) {
        for (PerfilType perfil : PerfilType.values()) {
            if (perfil.getDescricao().equalsIgnoreCase(descricao)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("Descrição de perfil inválida: " + descricao);
    }

    public static PerfilType fromId(Long id) {
        for (PerfilType perfil : PerfilType.values()) {
            if (perfil.getId().equals(id)) {
                return perfil;
            }
        }
        throw new IllegalArgumentException("ID de perfil inválido: " + id);
    }

    private static final Map<PerfilType, Set<PermissaoType>> perfilPermissaoMap = Map.of(
            PerfilType.ADMINISTRADOR, Set.of(
                    PermissaoType.CRIAR_USUARIOS,
                    PermissaoType.ATRIBUIR_PERFIS,
                    PermissaoType.MODIFICAR_PERFIS_EXISTENTES
            ),
            PerfilType.USUARIO_COMUM, Set.of(
                    PermissaoType.VISUALIZAR_INFORMACOES,
                    PermissaoType.ALTERAR_INFORMACOES
            )
    );

    public static Set<PermissaoType> getPermissoesParaPerfil(PerfilType perfilType) {
        return perfilPermissaoMap.getOrDefault(perfilType, Set.of());
    }
}
