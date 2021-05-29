package com.serratec.apirestfull.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.apirestfull.domain.Categoria;

@Repository
public interface ProdutoRepository extends JpaRepository<Categoria, Integer>{

	
		
}
