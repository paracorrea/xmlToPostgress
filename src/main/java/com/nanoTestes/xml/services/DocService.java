package com.nanoTestes.xml.services;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DocService {
	
	private final String directoryPath = "/home/fernando/Documentos/novo/folderFiles/nfe/";

	// este metodo le os arquivos xml e mostra na tela
	public void read() {
		
		try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(Paths.get(directoryPath), "*.{xml,XML}")) {
			
			processa(arquivos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public void processa(DirectoryStream<Path> arquivos) {
		
		for (Path arquivo : arquivos) {
			
			try {
				
				byte[] contentFile = Files.readAllBytes(arquivo);
				
				String contentFileString = new String(contentFile);
				
				System.out.println(contentFileString);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			//System.out.println(arquivo.getFileName().toString());
		}
		
	}
	

}
