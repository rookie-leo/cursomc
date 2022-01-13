package com.leonardo.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leonardo.cursomc.config.exceptions.ObjectNotFoundException;
import com.leonardo.cursomc.model.Categoria;
import com.leonardo.cursomc.model.Produto;
import com.leonardo.cursomc.repositories.CategoriaRepository;
import com.leonardo.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository catRepository;

	public Produto find(Long id) {
		Optional<Produto> produto = repository.findById(id);

		return produto.orElseThrow(() -> new ObjectNotFoundException("Produto não encontrado! id: " + id));
	}

	public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = catRepository.findAllById(ids);
		return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, request);
	}

}
