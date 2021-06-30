package com.serratec.apirestfull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.serratec.apirestfull.service.S3Service;

@SpringBootApplication
public class SerratecApplication implements CommandLineRunner {

	@Autowired private S3Service s3service;

	public static void main(String[] args) {
		SpringApplication.run(SerratecApplication.class, args);
	}

	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		s3service.uploadFile("C:\\Users\\Felipe\\Desktop\\ImagensS3\\ana.jpg");
		
	}

}
