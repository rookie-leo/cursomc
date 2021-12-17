package com.leonardo.cursomc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	
	public Estado(Long id, @NotBlank String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}	
	
}
