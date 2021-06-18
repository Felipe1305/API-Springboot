package com.serratec.apirestfull;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.domain.Cidade;
import com.serratec.apirestfull.domain.Cliente;
import com.serratec.apirestfull.domain.Endereco;
import com.serratec.apirestfull.domain.Estado;
import com.serratec.apirestfull.domain.ItemPedido;
import com.serratec.apirestfull.domain.Pagamento;
import com.serratec.apirestfull.domain.PagamentoComBoleto;
import com.serratec.apirestfull.domain.PagamentoComCartao;
import com.serratec.apirestfull.domain.Pedido;
import com.serratec.apirestfull.domain.Produto;
import com.serratec.apirestfull.domain.enums.EstadoPagamento;
import com.serratec.apirestfull.domain.enums.TipoCliente;
import com.serratec.apirestfull.repositories.CategoriaRepository;
import com.serratec.apirestfull.repositories.CidadeRepository;
import com.serratec.apirestfull.repositories.ClienteRepository;
import com.serratec.apirestfull.repositories.EnderecoRepository;
import com.serratec.apirestfull.repositories.EstadoRepository;
import com.serratec.apirestfull.repositories.ItemPedidoRepository;
import com.serratec.apirestfull.repositories.PagamentoRepository;
import com.serratec.apirestfull.repositories.PedidoRepository;
import com.serratec.apirestfull.repositories.ProdutoRepository;

@SpringBootApplication
public class SerratecApplication implements CommandLineRunner {

	

	public static void main(String[] args) {
		SpringApplication.run(SerratecApplication.class, args);
	}

	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		
	}

}
