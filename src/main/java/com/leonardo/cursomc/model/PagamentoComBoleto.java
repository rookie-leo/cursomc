package com.leonardo.cursomc.model;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.leonardo.cursomc.model.enuns.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PagamentoComBoleto extends Pagamento {

	private Date dataVencimento;
	private Date dataPagamento;

	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Long id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dataPagamento == null) ? 0 : dataPagamento.hashCode());
		result = prime * result + ((dataVencimento == null) ? 0 : dataVencimento.hashCode());
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
		PagamentoComBoleto other = (PagamentoComBoleto) obj;
		if (dataPagamento == null) {
			if (other.dataPagamento != null)
				return false;
		} else if (!dataPagamento.equals(other.dataPagamento))
			return false;
		if (dataVencimento == null) {
			if (other.dataVencimento != null)
				return false;
		} else if (!dataVencimento.equals(other.dataVencimento))
			return false;
		return true;
	}

	/**
	 * @param recebe uma instancia do pagamento com boleto
	 * @param recebe o momento em que o boleto foi gerado
	 * @method seta a data de pagamento do boleto para 7 dias a frente
	 * */
	public static void preencherPagamentoComBoleto(PagamentoComBoleto pgto, LocalDateTime instante) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(instante.getYear(), instante.getMonthValue() - 1, instante.getDayOfMonth(), instante.getHour(),
				instante.getMinute(), instante.getSecond());
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		
		pgto.setDataVencimento(calendar.getTime());
	}

}
