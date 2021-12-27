package com.leonardo.cursomc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.model.Pedido;
import com.leonardo.cursomc.repositories.PedidoRepository;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

	@Autowired
	private PedidoRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<Pedido> buscar(@PathVariable("id") Long id) {
		Optional<Pedido> pedido = repository.findById(id);

		if (pedido.isPresent()) {
			return ResponseEntity.ok().body(pedido.get());
		}

		return ResponseEntity.status(404).build();
	}

}
