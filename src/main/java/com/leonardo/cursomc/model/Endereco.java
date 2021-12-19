package com.leonardo.cursomc.model;

import java.util.Objects;

public class Endereco {

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private Cliente cliente;
    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String cep,
                    Cliente cliente, Cidade cidade) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cliente = cliente;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCep() {
        return cep;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Endereco endereco = (Endereco) o;
        return id.equals(endereco.id)
                && logradouro.equals(endereco.logradouro)
                && numero.equals(endereco.numero)
                && complemento.equals(endereco.complemento)
                && bairro.equals(endereco.bairro)
                && cep.equals(endereco.cep)
                && cliente.equals(endereco.cliente)
                && cidade.equals(endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, logradouro, numero, complemento, bairro, cep, cliente, cidade);
    }
}
