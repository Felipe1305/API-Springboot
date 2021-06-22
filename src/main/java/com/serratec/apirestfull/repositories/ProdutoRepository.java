package com.serratec.apirestfull.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Transactional
	@Query("SELECT DISTINCT obj FROM Produto obj WHERE obj.nome LIKE %:nome%")
	Page<Produto> search (@Param("nome") String nome, Pageable pageRequest);
		
	
		
}
