package com.leonardo.cursomc.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.cursomc.controller.dto.PedidoForm;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.model.Endereco;
import com.leonardo.cursomc.model.ItemPedido;
import com.leonardo.cursomc.model.PagamentoComBoleto;
import com.leonardo.cursomc.model.Pedido;
import com.leonardo.cursomc.repositories.ClienteRepository;
import com.leonardo.cursomc.repositories.EnderecoRepository;
import com.leonardo.cursomc.repositories.ItemPedidoRepository;
import com.leonardo.cursomc.repositories.PagamentoRepository;
import com.leonardo.cursomc.repositories.PedidoRepository;
import com.leonardo.cursomc.repositories.ProdutoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pgtoRepository;
	
	@Autowired
	private ProdutoRepository prodRepository;
	
	@Autowired
	private ItemPedidoRepository itemRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository endRepository;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscar(@PathVariable("id") Long id) {
		Optional<Pedido> pedido = repository.findById(id);

		if (pedido.isPresent()) {
			return ResponseEntity.ok().body(pedido.get());
		}

		return ResponseEntity.status(404).build();
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody PedidoForm form) {
		Cliente cliente = clienteRepository.findById(form.getClienteId()).get();
		Endereco endereco = endRepository.findById(form.getEnderecoEntregaId()).get();
		
		Pedido pedido = form.toModel(cliente, endereco, form.getPagamento());
		
		repository.save(pedido);
		
		if (pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pgto = (PagamentoComBoleto) pedido.getPagamento(); 
			PagamentoComBoleto.preencherPagamentoComBoleto(pgto, pedido.getInstante());
		}
		
		pgtoRepository.save(pedido.getPagamento());// está nulo
		
		for (ItemPedido item : pedido.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(prodRepository.findById(item.getProduto().getId()).get());
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(pedido);
		}
		
		itemRepository.saveAll(pedido.getItens());
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(pedido.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
