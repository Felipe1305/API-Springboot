package com.serratec.apirestfull.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.apirestfull.security.dto.LoginRequest;
import com.serratec.apirestfull.security.dto.LoginResponse;
import com.serratec.apirestfull.service.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private ClienteService servicoUsuario;
		
	@PostMapping
	public LoginResponse login (@RequestBody LoginRequest request) {		
		return servicoUsuario.logar(request.getEmail(), request.getSenha());
	}
		
}
