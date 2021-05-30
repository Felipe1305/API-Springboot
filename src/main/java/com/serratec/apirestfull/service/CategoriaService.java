package com.serratec.apirestfull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.DTO.CategoriaDTO;
import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.repositories.CategoriaRepository;
import com.serratec.apirestfull.service.exceptions.DataIntegrityException;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	public Categoria update(Categoria obj) {
		buscar(obj.getId());
		return repo.save(obj);
	}

	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um categoria que possui produtos!");
		}
	}

	public List<Categoria> buscarTodos() {
		return repo.findAll();
	}
	
	public Page<Categoria> buscandoPorPaginas(Integer page, Integer linhasPorPagina, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linhasPorPagina,Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}
}
