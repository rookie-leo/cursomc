package com.leonardo.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leonardo.cursomc.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
