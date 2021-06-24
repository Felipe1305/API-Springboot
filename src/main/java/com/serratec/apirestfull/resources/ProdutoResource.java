package com.serratec.apirestfull.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.serratec.apirestfull.DTO.ProdutoDTO;
import com.serratec.apirestfull.domain.Produto;
import com.serratec.apirestfull.resources.utils.URL;
import com.serratec.apirestfull.service.ProdutoService;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	List<ProdutoDTO> list = new ArrayList();

	@Autowired
	private ProdutoService service;
	
	@RequestMapping(value="/todos", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> getAll() {

		List<Produto> obj = service.findAll();
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value="/pesquisar",method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			
			@RequestParam(value = "linhasPorPagina", defaultValue = "24") Integer linhasPorPagina,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

//		List<Integer> ids = URL.decodeIntList(categorias);
		String nomeDecoded = URL.decodeParam(nome);
		
		Page<Produto> list = service.search(nomeDecoded, page, linhasPorPagina, orderBy, direction);
		Page<ProdutoDTO> listDTO = list.map(obj -> new ProdutoDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ProdutoDTO> update(@PathVariable Integer id, @RequestBody ProdutoDTO prodDTO) {
		return ResponseEntity.ok().header("Method: ", "Update").body(service.update(id, prodDTO));
	}
	
	@PostMapping("/carrinho")
	public void carrinho(@RequestBody ProdutoDTO prod){
		
		ProdutoDTO dto = new ProdutoDTO();
		dto.setId(prod.getId());
		dto.setNome(prod.getNome());
		dto.setDescricao(prod.getDescricao());
		dto.setPreco(prod.getPreco());
		dto.setUrlImagem(prod.getUrlImagem());
		list.add(dto);
	}
	
	@GetMapping("/carrinho")
	public List<ProdutoDTO> carrinhoRetorno(@RequestBody ProdutoDTO prod){
		
		return list;
	}
	
	@DeleteMapping("/carrinho/{id}")
	public void retirarItemCarrinho(@PathVariable Integer id) {
		for (ProdutoDTO prodDTO : list) {
			if(prodDTO.getId()==id) {
				list.remove(prodDTO);
			}
		}
	}
	@PutMapping("/carrinho/{id}")
	public void atualizarQuantidade(@PathVariable Integer id, @RequestBody ProdutoDTO prod) {
		for (ProdutoDTO prodUp : list) {
			if(prodUp.getId()==id) {
				prodUp.setQuantidadeEstoque(prod.getQuantidadeEstoque());
			}
		}
	}

}
