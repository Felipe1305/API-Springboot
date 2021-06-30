package com.serratec.apirestfull.resources;




import java.net.URI;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serratec.apirestfull.DTO.ClienteDTO;
import com.serratec.apirestfull.DTO.ClienteNewDTO;
import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.service.ClienteService;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		
		
		Cliente obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert( @RequestBody ClienteDTO objDTO) {
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDTO); 
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = service.buscarTodos();
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,@RequestParam(value="direction",defaultValue = "ASC") String direction) {

		Page<Cliente> list = service.buscandoPorPaginas(page,linhasPorPagina,orderBy,direction);
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
	
	@RequestMapping(value="/picture",method = RequestMethod.POST)
	public ResponseEntity<Void> uploadProfilePicture(@RequestParam(name="file") MultipartFile file) {
		URI uri = service.uploadPicture(file);
		return ResponseEntity.created(uri).build();
	}

}
