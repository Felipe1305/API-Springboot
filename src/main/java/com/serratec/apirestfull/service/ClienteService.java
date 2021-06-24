package com.serratec.apirestfull.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;

import org.springframework.stereotype.Service;

import com.serratec.apirestfull.DTO.ClienteDTO;
import com.serratec.apirestfull.DTO.ClienteNewDTO;
import com.serratec.apirestfull.domain.Cidade;
import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.domain.Endereco;
import com.serratec.apirestfull.domain.enums.TipoCliente;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.repositories.EnderecoRepository;


import com.serratec.apirestfull.service.exceptions.DataIntegrityException;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
//	private static final String headerPrefix = "Bearer ";
	
//	@Autowired
//	private JWTService jwtService;
	
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private PasswordEncoder passwordEnconder;
	@Autowired
	private ClienteRepository repo;
	
//	@Autowired
//	private EnderecoRepository repoEndreco;

		public Cliente buscar(Integer id) {
			Optional<Cliente> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Cliente não encontrado! ID: "+id+", Tipo: "+Cliente.class.getName()));
		}
		
		public Cliente insert(Cliente obj) {
			obj.setId(null);
			obj = repo.save(obj);
//			repoEndreco.saveAll(obj.getEnderecos());
			return obj;
		}
		
		public Cliente update(Cliente obj) {
			Cliente newObj = buscar(obj.getId());
			updateData(newObj,obj);
			return repo.save(newObj);
		}

		public void delete(Integer id) {
			buscar(id);
			try {
				repo.deleteById(id);
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
			}
		}

		public List<Cliente> buscarTodos() {
			return repo.findAll();
		}
		
		public Page<Cliente> buscandoPorPaginas(Integer page, Integer linhasPorPagina, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page, linhasPorPagina,Direction.valueOf(direction), orderBy);
			return repo.findAll(pageRequest);
		}
		
		public Cliente fromDTO(ClienteDTO objDTO) {
			
			return new Cliente(objDTO.getIdDTO(), objDTO.getNomeDTO(), objDTO.getEmailDTO(), null,null, objDTO.getSenha());
			
		}
		public Cliente fromDTO(ClienteNewDTO objDTO) {
			Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfouCnpj(),TipoCliente.toEnum(objDTO.getTipo()), objDTO.getSenha());
			Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
			Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(),objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
			cli.getEnderecos().add(end);
			cli.getTelefones().add(objDTO.getTelefone1());
			if(objDTO.getTelefone2() != null) {
				cli.getTelefones().add(objDTO.getTelefone2());
			}
			if(objDTO.getTelefone3() != null) {
				cli.getTelefones().add(objDTO.getTelefone3());
			}
			return cli;
		}
		
		private void updateData (Cliente newObj, Cliente obj) {
			newObj.setNome(obj.getNome());
			newObj.setEmail(obj.getEmail());
		}
//		public LoginResponse logar(String email, String senha) {
//			
//			var usuario = repo.findByEmail(email);
//			System.out.println(usuario.toString());
//
//			Authentication autenticacao = authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(email, senha, Collections.emptyList()));
//			
//			SecurityContextHolder.getContext().setAuthentication(autenticacao);
//			
//			String token = headerPrefix + jwtService.gerarToken(autenticacao);
//			
//			
//			return new LoginResponse(token, usuario.get());
//		}
}
