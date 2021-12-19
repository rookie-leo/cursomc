package com.leonardo.cursomc.model.enuns;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa física"),
    PESSOA_JURIDICA(2, "Pessoa juridica");

    private int cod;
    private String descricao;

    private TipoCliente(int cod, String descricao) {
        this.cod = cod;
        this.descricao = descricao;
    }

    public int getCod() {
        return this.cod;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public static TipoCliente toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }

        for (TipoCliente cliente : TipoCliente.values()) {
            if (cod.equals(cliente.getCod())) {
                return cliente;
            }
        }

        throw new IllegalArgumentException("Código não encontrado! " + cod);
    }
}
