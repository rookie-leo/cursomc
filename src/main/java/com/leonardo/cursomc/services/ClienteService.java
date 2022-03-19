package com.leonardo.cursomc.services;

import java.util.List;
import java.util.Optional;

import com.leonardo.cursomc.config.exceptions.DataIntegrityException;
import com.leonardo.cursomc.controller.dto.ClienteDTO;
import com.leonardo.cursomc.controller.dto.ClienteNewDTO;
import com.leonardo.cursomc.model.Cidade;
import com.leonardo.cursomc.model.Endereco;
import com.leonardo.cursomc.model.enuns.TipoCliente;
import com.leonardo.cursomc.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.leonardo.cursomc.config.exceptions.ObjectNotFoundException;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.repositories.ClienteRepository;

import javax.transaction.Transactional;

@Service
public class ClienteService {

	@Autowired 
	private ClienteRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public void delete(Long id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityException ex) {
			throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados!");
		}
	}

	public Cliente find(Long id) {
		Optional<Cliente> cliente = repository.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! id:" + id));
	}
	
	public List<Cliente> findAll() {
		return repository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(request);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	public Cliente fromDTO(ClienteNewDTO dto) {
		Cliente cliente = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getDocumento(),
				TipoCliente.toEnum(dto.getTipo()));
		Cidade cidade = new Cidade(dto.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
				dto.getBairro(), dto.getCep(), cliente, cidade);
		cliente.getEndereco().add(endereco);
		cliente.getTelefones().add(dto.getTelefone1());

		if (dto.getTelefone2() != null) {
			cliente.getTelefones().add(dto.getTelefone2());
		}
		if (dto.getTelefone3() != null) {
			cliente.getTelefones().add(dto.getTelefone3());
		}

		return cliente;
	}

	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = repository.save(cliente);
		enderecoRepository.saveAll(cliente.getEndereco());

		return cliente;
	}

	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);

		return repository.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}

}
