package com.leonardo.cursomc.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @NotNull Double preco;
	@ManyToMany
	@JoinTable(name = "PRODUTO_CATEGORIA",
				joinColumns = @JoinColumn(name = "produto_id"),
				inverseJoinColumns = @JoinColumn(name = "categoria_id"))
	private List<Categoria> categorias = new ArrayList<Categoria>();
	
	@Deprecated
	public Produto() {}
	
	public Produto(Long id, @NotBlank String nome, @NotNull Double preco, List<Categoria> categoria) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		categorias.addAll(categoria);
	}
	
	
	
}
