package com.leonardo.cursomc.controller.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.leonardo.cursomc.model.Categoria;
import com.sun.istack.NotNull;

public class CategoriaDTO {

	private @NotNull Long id;
	private @NotBlank @Length(min=1, max=80) String nome;
		
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
	
	public Categoria toModel() {
		return new Categoria(this.id, this.nome);
	}
	
}
