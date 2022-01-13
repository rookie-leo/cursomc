package com.leonardo.cursomc.controller.dto;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.model.Endereco;
import com.leonardo.cursomc.model.ItemPedido;
import com.leonardo.cursomc.model.Pagamento;
import com.leonardo.cursomc.model.Pedido;
import com.leonardo.cursomc.model.enuns.EstadoPagamento;

public class PedidoForm {

	@NotNull
	private Pagamento pagamento;
	@NotNull
	private Long clienteId;
	@NotNull
	private Long enderecoEntregaId;
	@NotNull
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();


	public PedidoForm() {
	}

	public PedidoForm(@NotNull Long clienteId, @NotNull Long enderecoEntregaId) {
		this.clienteId = clienteId;
		this.enderecoEntregaId = enderecoEntregaId;
		pagamento.setEstado(EstadoPagamento.PENDENTE);
	}

	public PedidoForm(@NotNull Pagamento pagamento, @NotNull Long clienteId, @NotNull Long enderecoEntregaId) {
		this.pagamento = pagamento;
		this.clienteId = clienteId;
		this.enderecoEntregaId = enderecoEntregaId;
	}

	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

	public Long getEnderecoEntregaId() {
		return enderecoEntregaId;
	}

	public void setEnderecoEntregaId(Long enderecoEntregaId) {
		this.enderecoEntregaId = enderecoEntregaId;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

	public Pedido toModel(Cliente cliente, Endereco endereco, Pagamento pgto) {
		return new Pedido(pgto, cliente, endereco);
	}

}
