package com.serratec.apirestfull.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.apirestfull.domain.Cidade;
import com.serratec.apirestfull.domain.Produto;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	
		
}
