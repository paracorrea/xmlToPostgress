
package com.nanoTestes.xml;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nanoTestes.xml.services.DocService;

@SpringBootApplication
public class XmlToPostgresApplication implements CommandLineRunner{

	// instancia na varDir o path
	
	@Autowired
	private DocService docService;
	
	public static void main(String[] args) {
		SpringApplication.run(XmlToPostgresApplication.class, args);
		
		
	}

	
	// Inserir implements CommandLineRunner na Classe principal
	@Override
	public void run(String... args) throws Exception {
		
		docService.read();
			
		} 
		
	}

	
