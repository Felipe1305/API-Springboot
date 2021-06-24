package com.serratec.apirestfull.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.apirestfull.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Transactional
	Optional<Cliente> findByEmail(String email);
	
//	Optional<Cliente> findById(Long id);
	

	
		
}
