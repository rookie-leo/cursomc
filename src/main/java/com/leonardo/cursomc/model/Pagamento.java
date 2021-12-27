package com.leonardo.cursomc.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.leonardo.cursomc.model.enuns.EstadoPagamento;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento {

	@Id
	private Long id;
	private Integer estado;
	@JsonBackReference
	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento() {}

	public Pagamento(Long id, EstadoPagamento estado, Pedido pedido) {
		super();
		this.id = id;
		this.estado = estado.getCod();
		this.pedido = pedido;
	}

	public Long getId() {
		return id;
	}

	public EstadoPagamento getEstado() {
		return EstadoPagamento.toEnum(estado);
	}

	public Pedido getPedido() {
		return pedido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((pedido == null) ? 0 : pedido.hashCode());
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
		Pagamento other = (Pagamento) obj;
		if (estado != other.estado)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (pedido == null) {
			if (other.pedido != null)
				return false;
		} else if (!pedido.equals(other.pedido))
			return false;
		return true;
	}
	
	
	
}
