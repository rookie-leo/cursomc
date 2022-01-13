package com.leonardo.cursomc.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.services.validation.ClienteUpdate;

@ClienteUpdate
public class ClienteDTO {

	private Long id;
	private @NotBlank @Length(min=1, max=80) String nome;
	private @NotBlank @Length(min=1, max=80) @Email String email;
	
	@Deprecated
	public ClienteDTO() {}

	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getId();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
	}
	
	public ClienteDTO(Long id, @NotBlank @Length(min=1, max=80) String nome, @NotBlank @Length(min=1, max=80) @Email String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Cliente toModel(ClienteDTO dto) {
		return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
	}
		
}
