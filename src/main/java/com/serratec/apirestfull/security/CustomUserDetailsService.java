package com.serratec.apirestfull.security;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.repositories.UsuarioRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository repositorioUsuario;
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Override
	public UserDetails loadUserByUsername(String email) {
		Cliente usuario = getUser(() -> repoCliente.findByEmail(email));
		return usuario;
	}
	
	public UserDetails pegarUsuarioPorId(Long id) {
		Cliente usuario = getUser(() -> repositorioUsuario.findById(id));
		return usuario;
	}
	
	
	private Cliente getUser(Supplier<Optional<Cliente>> supplier) {
		return supplier.get().orElseThrow(() -> 
				new UsernameNotFoundException("Usuário não encontrado"));
	}

}
