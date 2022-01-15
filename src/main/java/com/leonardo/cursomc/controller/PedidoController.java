package com.leonardo.cursomc.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import com.leonardo.cursomc.services.PedidoService;
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
	private PedidoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> find(@PathVariable("id") Long id) {
		Pedido pedido = service.find(id);

		return ResponseEntity.status(404).build();
	}

	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido pedido) {
		pedido = service.insert(pedido);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("{id}")
				.buildAndExpand(pedido.getId())
				.toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
}
