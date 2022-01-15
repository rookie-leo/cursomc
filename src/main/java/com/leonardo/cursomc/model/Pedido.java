package com.leonardo.cursomc.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime instante =  LocalDateTime.now();  
	@OneToOne(optional = true, cascade=CascadeType.ALL, mappedBy="pedido")
	private Pagamento pagamento;
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name = "endereco_de_entrega_id")
	private Endereco enderecoEntrega;
	@OneToMany(mappedBy = "id.pedido")
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();
	
	@Deprecated
	public Pedido() {}

	public Pedido(Long id, Cliente cliente, Endereco enderecoEntrega) {
		this.id = id;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Pedido(Pagamento pagamento, Cliente cliente, Endereco enderecoEntrega) {
		this.pagamento = pagamento;
		this.cliente = cliente;
		this.enderecoEntrega = enderecoEntrega;
	}
	
	public Long getId() {
		return id;
	}

	public LocalDateTime getInstante() {
		return instante;
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

	public Set<ItemPedido> getItens() {
		return itens;
	}
		
	public void setId(Long id) {
		this.id = id;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getValorTotal() {
		Double soma = 0.0;
		for (ItemPedido item : itens) {
			soma += item.getSubtotal();
		}
		
		return soma;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
		
}
