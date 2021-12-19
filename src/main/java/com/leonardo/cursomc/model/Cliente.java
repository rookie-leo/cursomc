package com.leonardo.cursomc.model;

import com.leonardo.cursomc.model.enuns.TipoCliente;

import javax.persistence.*;
import java.util.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String documento;
    private Integer tipo;
    @OneToMany(mappedBy = "cliente")
    private List<Endereco> endereco = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "TELEFONES")
    private Set<String> telefones = new HashSet<>();

    public Cliente() {}

    public Cliente(Long id, String nome, String email, String documento, TipoCliente tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.documento = documento;
        this.tipo = tipo.getCod();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public TipoCliente getTipo() {
        return TipoCliente.toEnum(tipo);
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public Set<String> getTelefones() {
        return telefones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return id.equals(cliente.id)
                && nome.equals(cliente.nome)
                && email.equals(cliente.email)
                && documento.equals(cliente.documento)
                && tipo.equals(cliente.tipo)
                && endereco.equals(cliente.endereco)
                && telefones.equals(cliente.telefones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, documento, tipo, endereco, telefones);
    }
}
