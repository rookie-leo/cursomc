package com.leonardo.cursomc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.controller.dto.ProdutoDTO;
import com.leonardo.cursomc.controller.utils.URL;
import com.leonardo.cursomc.model.Produto;
import com.leonardo.cursomc.services.ProdutoService;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService service;

	@GetMapping("/{id}")
	public ResponseEntity<Produto> find(@PathVariable("id") Long id) {
		Produto produto = service.find(id);

		return ResponseEntity.ok().body(produto);
	}

	@GetMapping
	public ResponseEntity<Page<ProdutoDTO>> search(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		String decode = URL.decodeParam(nome);
		List<Long> ids = URL.decodeLongList(categorias);
		Page<Produto> produtos = service.search(decode, ids, page, linesPerPage, orderBy, direction);
		Page<ProdutoDTO> dtos = produtos.map(dto -> new ProdutoDTO(dto));

		return ResponseEntity.ok().body(dtos);
	}

}
