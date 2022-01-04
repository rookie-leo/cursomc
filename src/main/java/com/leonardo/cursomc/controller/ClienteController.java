package com.leonardo.cursomc.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leonardo.cursomc.config.exceptions.DataIntegrityException;
import com.leonardo.cursomc.config.exceptions.ObjectNotFoundException;
import com.leonardo.cursomc.controller.dto.ClienteDTO;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteRepository repository;
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> search(@PathVariable("id") Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		if (cliente.isPresent()) {
			return ResponseEntity.status(200).body(cliente.get());
		}
		
		return ResponseEntity.status(404).build();
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody ClienteDTO request, @PathVariable Long id) {
		Optional<Cliente> cliente = repository.findById(id);

		if (cliente.isPresent()) {
			Cliente atualiza = cliente.get();
			
			if (request.getNome() != null) {
				atualiza.setNome(request.getNome());				
			}
			
			if (request.getEmail() != null) {
				atualiza.setEmail(request.getEmail());
			}
			
			repository.save(atualiza);
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);

		} catch (DataIntegrityViolationException | ConstraintViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir esse cliente!");
		} catch (EmptyResultDataAccessException ex) {
			throw new ObjectNotFoundException("Cliente não encontrado!");
		}

		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public List<ClienteDTO> findAll() {
		List<Cliente> lista = repository.findAll();
		List<ClienteDTO> clientes = lista.stream().map(c -> new ClienteDTO(c)).collect(Collectors.toList());

		return clientes;
	}

	@GetMapping("/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<Cliente> categorias = repository.findAll(request);
		Page<ClienteDTO> clientesDTO = categorias.map(dto -> new ClienteDTO(dto));

		return ResponseEntity.ok().body(clientesDTO);
	}
	
}
