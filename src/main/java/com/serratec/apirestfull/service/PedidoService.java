package com.serratec.apirestfull.service;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serratec.apirestfull.domain.ItemPedido;
import com.serratec.apirestfull.domain.PagamentoComBoleto;
import com.serratec.apirestfull.domain.Pedido;
import com.serratec.apirestfull.domain.enums.EstadoPagamento;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.repositories.ItemPedidoRepository;
import com.serratec.apirestfull.repositories.PagamentoRepository;
import com.serratec.apirestfull.repositories.PedidoRepository;
import com.serratec.apirestfull.repositories.ProdutoRepository;
import com.serratec.apirestfull.service.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired 
	private BoletoService boletoService;
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired PagamentoRepository pagtoRepo;
	
	@Autowired ProdutoService prodService;
	
	@Autowired
	private ItemPedidoRepository itemRepo;
	
	@Autowired ClienteRepository cliRepo;
	
	@Autowired ProdutoRepository prodRepo;
	
	@Autowired private EmailService emailService;

		public Pedido buscar(Integer id) {
			Optional<Pedido> obj = repo.findById(id);
			return obj.orElseThrow(() -> new ObjectNotFoundException(
					"Pedido não encontrado! ID: "+id+", Tipo: "+Pedido.class.getName()));
		}
		
		@Transactional
		public @Valid Pedido insert(@Valid Pedido obj) {
			obj.setId(null);
			obj.setInstante(new Date());
			obj.setCliente(cliRepo.findById(obj.getCliente().getId()).get());
			obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
			obj.getPagamento().setPedido(obj);
			if(obj.getPagamento() instanceof PagamentoComBoleto ) {
				PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
				boletoService.preencherPagamentoComBoleto(pagto,obj.getInstante());
			}
			obj = repo.save(obj);
			pagtoRepo.save(obj.getPagamento());
			
			for (ItemPedido ip : obj.getItens()) {
				ip.setDesconto(0.0);
//				ip.setPreco(prodService.buscar(ip.getProduto().getId()).getPreco());
				ip.setProduto(prodService.buscar(ip.getProduto().getId()));
				ip.setPreco(ip.getProduto().getPreco());
				ip.setPedido(obj);
			}
			itemRepo.saveAll(obj.getItens());
			
			emailService.sendOrderConfirmationEmail(obj);
			return obj;
		}
}
