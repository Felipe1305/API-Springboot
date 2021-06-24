package com.serratec.apirestfull.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serratec.apirestfull.domain.Cliente;



@Repository
public interface UsuarioRepository extends JpaRepository<Cliente, Long> {
	Optional<Cliente> findById(Long id);
	Optional<Cliente> findByEmail(String email);
}
