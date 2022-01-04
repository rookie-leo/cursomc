package com.leonardo.cursomc.controller.dto;

import com.leonardo.cursomc.model.Categoria;

public class CategoriaDTO {

	private Long id;
	private String nome;
		
	public CategoriaDTO() {
	}

	public CategoriaDTO(Categoria categoria) {
		super();
		this.id = categoria.getId();
		this.nome = categoria.getNome();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}
	
}
