package com.serratec.apirestfull.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.apirestfull.DTO.CidadeDTO;
import com.serratec.apirestfull.DTO.EstadoDTO;
import com.serratec.apirestfull.domain.Cidade;
import com.serratec.apirestfull.domain.Estado;
import com.serratec.apirestfull.service.CidadeService;
import com.serratec.apirestfull.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {

	
	@Autowired EstadoService service;
	
	@Autowired CidadeService cidadeService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> list = service.findAll();
		List<EstadoDTO> listDTO = list.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	@RequestMapping(value="/{estadoId}/cidades",method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> list = cidadeService.findAll(estadoId);
		List<CidadeDTO> listDTO = list.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	
}
