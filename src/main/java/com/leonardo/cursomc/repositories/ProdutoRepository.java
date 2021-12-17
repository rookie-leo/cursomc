package com.leonardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.cursomc.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
