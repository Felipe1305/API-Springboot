package com.serratec.apirestfull.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;

		public Cliente buscar(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Cliente não encontrado! ID: "+id+", Tipo: "+Cliente.class.getName()));
		}
}
