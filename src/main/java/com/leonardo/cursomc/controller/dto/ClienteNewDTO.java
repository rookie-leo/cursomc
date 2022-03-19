package com.leonardo.cursomc.controller.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.leonardo.cursomc.model.Cidade;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.model.Endereco;
import com.leonardo.cursomc.model.enuns.TipoCliente;
import com.leonardo.cursomc.services.validation.ClienteInsert;

@ClienteInsert
public class ClienteNewDTO {

	@NotBlank @Length(min=1, max=80) 
	private String nome;
	
	@NotBlank @Length(min=1, max=80) @Email
	private String email;
	
	@NotBlank
	private String documento;
	
	@NotNull
	private Integer tipo;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	@NotNull
	private Long cidadeId;
	
	public ClienteNewDTO() {}

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

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	public Cliente toModel() {
		Cliente cliente = new Cliente(null, nome, email, documento, TipoCliente.toEnum(tipo));
		Cidade cidade = new Cidade(cidadeId, null, null);
		Endereco endereco = new Endereco(null, logradouro, numero, complemento, bairro, cep, cliente, cidade);
		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(this.telefone1);
		if (this.telefone2 != null) {
			cliente.getTelefones().add(this.telefone2);
		}
		if (this.telefone3 != null) {
			cliente.getTelefones().add(this.telefone3);
		}
		
		return cliente;
	}
	
}
