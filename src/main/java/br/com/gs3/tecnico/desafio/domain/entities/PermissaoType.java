package br.com.gs3.tecnico.desafio.domain.entities;

public enum PermissaoType {
    CRIAR_USUARIOS(1L, "Criar usuários"),
    ATRIBUIR_PERFIS(2L, "Atribuir perfis"),
    MODIFICAR_PERFIS_EXISTENTES(3L, "Modificar perfis existentes"),
    VISUALIZAR_INFORMACOES(4L, "Visualizar informações"),
    ALTERAR_INFORMACOES(5L, "Alterar informações");

    private final Long codigo;
    private final String descricao;

    PermissaoType(Long codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Long getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static PermissaoType fromCodigo(Long codigo) {
        for (PermissaoType tipo : values()) {
            if (tipo.getCodigo().equals(codigo)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código não encontrado: " + codigo);
    }
}
