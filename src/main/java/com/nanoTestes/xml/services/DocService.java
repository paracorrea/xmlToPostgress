package com.nanoTestes.xml.services;



/*
 * Service 
 * 
 *
 * The class DocService start instancing a xmlToObject where the XML object is created and prepar java methods 
	for transform at here a file to the xml document and start reader at own
 *	
*/


import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nanoTestes.xml.services.xmlToObject.XmlToObject;

@Service
public class DocService {
	

	
	private final String directoryPath = "/home/fernando/Documentos/novo/folderFiles/nfe/";
	//private final String directoryPath = "C:\\Users\\fernando.correa\\Documents\\GitHub\\novos\\folder\\";
	
	// Instancia um objeto xmlToObject with XmlToObject
	@Autowired
	private XmlToObject xmlToObject;
	
	
	// this method save all files in variable local arquivos type DirectoryStream Path 
	// instanciada seu conteudo com Files.newDirectotyStream, making a file of the stream with files xml of the directory
	// after call the method processa reciving the stream
	public void read() {
		
		try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(Paths.get(directoryPath), "*.{xml,XML}")) {
			
			
			processa(arquivos);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	// the method reciving a Directory stream contend the files xml and convert in contentFile each file in a array of the
	// bytes and call the class XmlToObject with a array of the byte for each xml read
	public void processa(DirectoryStream<Path> arquivos) {
		
		for (Path arquivo : arquivos) {
			
			try {
				
				// convert o arquivo em tipo Byte
				byte[] contentFile = Files.readAllBytes(arquivo);
				
				// Call the method nfeMapping in the xmlToObject with the array of the byte	
				
				
				xmlToObject.nfeMapping(contentFile);
				
				
			} catch (Exception e) {
				
				System.out.println("Erro o ler o arquivo: " +arquivo.getFileName());
			}
			
		}
	} // close method processa()
	

	

}
