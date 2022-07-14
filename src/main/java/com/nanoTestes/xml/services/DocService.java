package com.nanoTestes.xml.services;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nanoTestes.xml.services.xmlToObject.XmlToObject;

@Service
public class DocService {
	
	private final String directoryPath = "/home/fernando/Documentos/novo/folderFiles/nfe/";
	
	@Autowired
	private XmlToObject xmlToObject;
	
	
	// este metodo le os arquivos xml e mostra na tela
	public void read() {
		
		try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(Paths.get(directoryPath), "*.{xml,XML}")) {
			
			processa(arquivos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	// processa o conteudo do arquivo
	public void processa(DirectoryStream<Path> arquivos) {
		
		for (Path arquivo : arquivos) {
			
			try {
				
				// convert o arquivo em tipo Byte
				byte[] contentFile = Files.readAllBytes(arquivo);
				
				//String contentFileString = new String(contentFile);
				//System.out.println(contentFileString);
				
				xmlToObject.nfeMapping(contentFile);
				
				
			} catch (Exception e) {
				
				System.out.println("Erro o ler o arquivo: " +arquivo.getFileName());
			}
			//System.out.println(arquivo.getFileName().toString());
		}
		
		
	
		
	}
	

}
