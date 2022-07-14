
package com.nanoTestes.xml;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.nanoTestes.xml.services.DocService;

@SpringBootApplication
public class XmlToPostgresApplication implements CommandLineRunner{

	
	
	@Autowired
	private DocService docService;
	
	public static void main(String[] args) {
		SpringApplication.run(XmlToPostgresApplication.class, args);
		
		
	}

	/*
	   The Apllication start with call for the class DocService, instanciada aqui em docService
	   in the method run here 
	*/
	@Override
	public void run(String... args) throws Exception {
		
		docService.read();
			
		} 
		
	}

	

