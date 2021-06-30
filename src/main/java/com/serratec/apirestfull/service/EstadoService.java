package com.serratec.apirestfull.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Estado;
import com.serratec.apirestfull.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired 
	private EstadoRepository repo;
	
	public List<Estado> findAll(){
		return repo.findAllByOrderByNome();
	}

}
