package com.leonardo.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.leonardo.cursomc.controller.dto.ClienteNewDTO;
import com.leonardo.cursomc.controller.exception.FieldMessage;
import com.leonardo.cursomc.model.Cliente;
import com.leonardo.cursomc.model.enuns.TipoCliente;
import com.leonardo.cursomc.repositories.ClienteRepository;
import com.leonardo.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository repository;
	
	@Override
	public void initialize(ClienteInsert constraintAnnotation) {
	}

	@Override
	public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<FieldMessage>();

		if (dto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(dto.getDocumento())) {
			list.add(new FieldMessage("documento", "Documento inválido!"));
		}

		if (dto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(dto.getDocumento())) {
			list.add(new FieldMessage("documento", "Documento inválido!"));
		}
		
		Cliente cliente = repository.findByEmail(dto.getEmail());
		
		if (cliente != null) {
			list.add(new FieldMessage("email", "Email já cadastrado!"));
		}

		for (FieldMessage msg : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(msg.getMessage())
					.addPropertyNode(msg.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}

}
