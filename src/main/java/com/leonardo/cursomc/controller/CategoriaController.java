package com.leonardo.cursomc.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.model.Categoria;
import com.leonardo.cursomc.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
		Optional<Categoria> categoria = repository.findById(id);

		if (categoria.isPresent()) {
			return ResponseEntity.ok().body(categoria.get());
		}

		return ResponseEntity.status(404).build();
	}

}
