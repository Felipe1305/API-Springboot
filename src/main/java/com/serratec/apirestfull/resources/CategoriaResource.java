package com.serratec.apirestfull.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.apirestfull.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List <Categoria> listar() {
		List<Categoria> lista = new ArrayList<>();
		
		Categoria cat1 = new Categoria(1, "Carro");
		
		lista.add(cat1);
		
		return lista;
	}
	

}
