package com.leonardo.cursomc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.controller.dto.ProdutoDTO;
import com.leonardo.cursomc.controller.utils.URL;
import com.leonardo.cursomc.model.Categoria;
import com.leonardo.cursomc.model.Produto;
import com.leonardo.cursomc.repositories.CategoriaRepository;
import com.leonardo.cursomc.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable("id") Long id) {
		Optional<Produto> produto = repository.findById(id);

		if (produto.isPresent()) {
			return ResponseEntity.ok().body(produto.get());
		}

		return ResponseEntity.status(404).build();
	}
	
	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> search(
			@RequestParam(value = "nome", defaultValue = "") String nome, 
			@RequestParam(value = "categorias", defaultValue = "") String categorias, 
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Pageable request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		String decoded = URL.decodeParam(nome);
		List<Long> ids = URL.decodeLongList(categorias);
		List<Categoria> categoria = categoriaRepo.findAllById(ids);
		Page<Produto> produtos = repository.findDistinctByNomeContainingAndCategoriasIn(decoded, categoria, request);
		Page<ProdutoDTO> dtos = produtos.map(dto -> new ProdutoDTO(dto));
		
		return ResponseEntity.ok().body(dtos);
	}
	
}
