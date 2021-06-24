package com.serratec.apirestfull.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.serratec.apirestfull.DTO.ClienteDTO;
import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.resources.exceptions.FieldMessage;

public class ClienteUpdatetValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	private HttpServletRequest request;
	
	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
	
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId =Integer.parseInt(map.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();

	
		
		// inclua os testes aqui, inserindo erros na lista
		
		Optional<Cliente> aux = repo.findByEmail(objDto.getEmailDTO());
		if(aux != null && !aux.get().getId().equals(uriId)) {
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
