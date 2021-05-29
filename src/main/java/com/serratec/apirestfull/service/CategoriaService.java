package com.serratec.apirestfull.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.repositories.CategoriaRepository;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;

		public Categoria buscar(Integer id) {
			Optional<Categoria> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Objeto não encontrado! ID: "+id+", Tipo: "+Categoria.class.getName()));
		}
}
