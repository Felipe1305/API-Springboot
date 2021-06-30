package com.serratec.apirestfull.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Cidade;
import com.serratec.apirestfull.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired 
	private CidadeRepository repo;
	
	public List<Cidade> findAll(Integer estadoId){
		return repo.findCidadesByEstadoId(estadoId);
	}

}
