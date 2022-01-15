package com.leonardo.cursomc.controller;

import com.leonardo.cursomc.config.exceptions.DataIntegrityException;
import com.leonardo.cursomc.config.exceptions.ObjectNotFoundException;
import com.leonardo.cursomc.controller.dto.ClienteDTO;
import com.leonardo.cursomc.controller.dto.ClienteNewDTO;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable("id") Long id) {
        Cliente cliente = service.find(id);

        return ResponseEntity.ok().body(cliente);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO dto) {
        Cliente cliente = service.fromDTO(dto);
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{/id}")
                .buildAndExpand(cliente.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody ClienteDTO request, @PathVariable Long id) {
        Cliente cliente = service.fromDTO(request);
        cliente.setId(id);
        cliente = service.update(cliente);
        return ResponseEntity.noContent().build();
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
       List<Cliente> clientes = service.findAll();
       List<ClienteDTO> dtos = clientes.stream()
               .map(dto -> new ClienteDTO(dto))
               .collect(Collectors.toList());

        return ResponseEntity.ok().body(dtos);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                     @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                     @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
                                                     @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> categorias = service.findPage(page, linesPerPage, direction, orderBy);
        Page<ClienteDTO> clientesDTO = categorias.map(dto -> new ClienteDTO(dto));

        return ResponseEntity.ok().body(clientesDTO);
    }

}
