package com.leonardo.cursomc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.model.Categoria;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

	@GetMapping()
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1, "Informatica");
		Categoria c2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<Categoria>();
		lista.add(c1);
		lista.add(c2);
		
		return lista;
	}
}
