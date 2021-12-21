package com.leonardo.cursomc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.status(200).body(cliente.get());
		}
		
		return ResponseEntity.status(404).build();
		
	}
	
}
