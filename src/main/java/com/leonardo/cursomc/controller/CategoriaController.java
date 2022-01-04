package com.leonardo.cursomc.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.leonardo.cursomc.config.exceptions.DataIntegrityException;
import com.leonardo.cursomc.model.Categoria;
import com.leonardo.cursomc.repositories.CategoriaRepository;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> search(@PathVariable("id") Long id) {
		Optional<Categoria> categoria = repository.findById(id);

		if (categoria.isPresent()) {
			return ResponseEntity.ok().body(categoria.get());
		}

		return ResponseEntity.status(404).build();
	}

	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
		repository.save(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody Categoria request, @PathVariable Long id) {
		Optional<Categoria> categoria = repository.findById(id);

		if (categoria.isPresent()) {
			Categoria novoNome = categoria.get();
			novoNome.setNome(request.getNome());
			repository.save(novoNome);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);
						
		} catch (DataIntegrityViolationException | ConstraintViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir essa categoria!");
		}
		return ResponseEntity.noContent().build();
		
	}

}
