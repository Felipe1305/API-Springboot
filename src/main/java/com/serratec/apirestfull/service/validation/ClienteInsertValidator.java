package com.serratec.apirestfull.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.serratec.apirestfull.DTO.ClienteNewDTO;
import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.domain.enums.TipoCliente;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.resources.exceptions.FieldMessage;
import com.serratec.apirestfull.service.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod())&& !BR.isValidCPF(objDto.getCpfouCnpj())) {
		
			list.add(new FieldMessage("Cpf0uCnpj", "Cpf inválido"));
			}
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod())&& !BR.isValidCNPJ(objDto.getCpfouCnpj())) {
			
			list.add(new FieldMessage("Cpf0uCnpj", "Cnpj inválido"));
			}
		
		// inclua os testes aqui, inserindo erros na lista
		
		Cliente aux = repo.findByEmail(objDto.getEmail());
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existente!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
