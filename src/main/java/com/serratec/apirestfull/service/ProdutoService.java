package com.serratec.apirestfull.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.DTO.ProdutoDTO;
import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.domain.Produto;
import com.serratec.apirestfull.repositories.CategoriaRepository;
import com.serratec.apirestfull.repositories.ProdutoRepository;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repo;
	
	@Autowired
	private CategoriaRepository categoriaRepo;

		public Produto buscar(Integer id) {
			Optional<Produto> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Produto não encontrado! ID: "+id+", Tipo: "+Produto.class.getName()));
		}
		
		public Page<Produto> search(String nome, Integer page, Integer linhasPorPagina, String orderBy, String direction){
			PageRequest pageRequest = PageRequest.of(page, linhasPorPagina,Direction.valueOf(direction), orderBy);
//			List<Categoria> categorias = categoriaRepo.findAllById(ids);
			return repo.search(nome,pageRequest);
		}

		public List<Produto> findAll() {
			// TODO Auto-generated method stub
			List<Produto> list = repo.findAll();
			return list;
		}

		public ProdutoDTO update(Integer id, ProdutoDTO prodDTO) {
			
				buscar(id);
				ProdutoDTO  prodNew = prodDTO;
				
				Produto prod = repo.findById(id).get();
				
				if(prodNew.getNome() != null) {
					prod.setNome(prodNew.getNome());
				}
				
				if(prodNew.getDescricao()!= null) {
					prod.setDescricao(prodNew.getDescricao());
				}
				if(prodNew.getUrlImagem()!= null) {
					prod.setUrlImagem(prodNew.getUrlImagem());
				}
				if(prodNew.getPreco()!= null) {
					prod.setPreco(prodNew.getPreco());
				}
				
				repo.save(prod);
				return prodNew;
			
		}
}
