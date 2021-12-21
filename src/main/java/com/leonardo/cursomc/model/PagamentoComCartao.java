package com.leonardo.cursomc.model;

import com.leonardo.cursomc.model.enuns.EstadoPagamento;

public class PagamentoComCartao extends Pagamento{

	private Integer numeroParcelas;
	
	public PagamentoComCartao() {}

	public PagamentoComCartao(Long id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
		super(id, estado, pedido);
		this.numeroParcelas = numeroParcelas;
	}

	public Integer getNumeroParcelas() {
		return numeroParcelas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((numeroParcelas == null) ? 0 : numeroParcelas.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PagamentoComCartao other = (PagamentoComCartao) obj;
		if (numeroParcelas == null) {
			if (other.numeroParcelas != null)
				return false;
		} else if (!numeroParcelas.equals(other.numeroParcelas))
			return false;
		return true;
	}
	
}
