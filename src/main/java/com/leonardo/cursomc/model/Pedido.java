package com.leonardo.cursomc.model;

import java.time.LocalDateTime;

public class Pedido {

	private Long id;
	private LocalDateTime instatne =  LocalDateTime.now();
	private Pagamento pagamento;
	private Cliente cliente;
	private Endereco enderecoEntrega;
	
	public Pedido() {}

	public Pedido(Long id, LocalDateTime instatne, Pagamento pagamento, Cliente cliente, Endereco enderecoEntrega) {
		super();
		this.id = id;
		this.instatne = instatne;
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}
	
	

	public Long getId() {
		return id;
	}

	public LocalDateTime getInstatne() {
		return instatne;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
		result = prime * result + ((enderecoEntrega == null) ? 0 : enderecoEntrega.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((instatne == null) ? 0 : instatne.hashCode());
		result = prime * result + ((pagamento == null) ? 0 : pagamento.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		if (enderecoEntrega == null) {
			if (other.enderecoEntrega != null)
				return false;
		} else if (!enderecoEntrega.equals(other.enderecoEntrega))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (instatne == null) {
			if (other.instatne != null)
				return false;
		} else if (!instatne.equals(other.instatne))
			return false;
		if (pagamento == null) {
			if (other.pagamento != null)
				return false;
		} else if (!pagamento.equals(other.pagamento))
			return false;
		return true;
	}
	
	
	
}
