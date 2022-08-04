package com.nanoTestes.xml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nanoTestes.xml.services.DocService;

@Controller
//@RequestMapping("/home")
public class DocController {

	@Autowired
	private DocService docService;	
	
	@GetMapping("/home")
	public String home(){
		
		//docService.read();
		
		
		return "home";
		
	}
	
	
	@GetMapping("/lerArquivos")
	public String lerArquivos() {
		
		docService.read();
		return "home";
	}
}
