package com.leonardo.cursomc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Entity
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String nome;
	private @ManyToOne @Valid Estado estado;
	
	public Cidade() {}
	
	public Cidade(Long id, @NotBlank String nome, @Valid Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}	
	
}
