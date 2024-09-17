package br.com.gs3.tecnico.desafio.domain.entities;

public enum PermissaoType {
    CRIAR_USUARIOS(1L, "CRIAR_USUARIOS"),
    ATRIBUIR_PERFIS(2L, "ATRIBUIR_PERFIS"),
    MODIFICAR_PERFIS_EXISTENTES(3L, "MODIFICAR_PERFIS_EXISTENTES"),
    VISUALIZAR_INFORMACOES(4L, "VISUALIZAR_INFORMACOES"),
    ALTERAR_INFORMACOES(5L, "ALTERAR_INFORMACOES");

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
