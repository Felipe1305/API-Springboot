package com.serratec.apirestfull.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.Pedido;
import com.serratec.apirestfull.repositories.PedidoRepository;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;

		public Pedido buscar(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Pedido não encontrado! ID: "+id+", Tipo: "+Pedido.class.getName()));
		}
}
