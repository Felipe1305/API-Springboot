package com.serratec.apirestfull;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serratec.apirestfull.domain.Categoria;
import com.serratec.apirestfull.repositories.CategoriaRepository;

@SpringBootApplication
public class SerratecApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SerratecApplication.class, args);
	}

	
	
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		categoriaRepo.saveAll(Arrays.asList(cat1,cat2));
		
	}
	
	

}

