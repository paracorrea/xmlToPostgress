package com.nanoTestes.xml.services;



/*
 * Service 
 * 
 *
 * The class DocService start instancing a xmlToObject where the XML object is created and prepar java methods 
	for transform at here a file to the xml document and start reader at own
 *	
*/
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nanoTestes.xml.services.xmlToObject.XmlToObject;

@Service
public class DocService {
	
	static final Logger LOG = LogManager.getLogger(DocService.class.getName());
	
	//private final String directoryPath = "/home/fernando/Documentos/novo/folderFiles/nfe/";
	private final String directoryPath = "C:\\Users\\fernando.correa\\Documents\\GitHub\\novos\\folder\\";
	
	
	
	@Autowired
	private XmlToObject xmlToObject;
	
	
	public void read() {
		
		try (DirectoryStream<Path> arquivos = Files.newDirectoryStream(Paths.get(directoryPath), "*.{xml,XML}")) {
			
			//processaII(arquivos);
			processa(arquivos);
			
		} catch (IOException e) {
			
			LOG.info("O ditório é inexistente ou outro problema"+ e.getMessage());
			
		}
	
	}
	

	public void processa(DirectoryStream<Path> arquivos) {
		
		for (Path arquivo : arquivos) {
			
			try {
				
				// convert o arquivo em tipo Byte
				byte[] contentFile = Files.readAllBytes(arquivo);
				
					
				// Call the method nfeMapping in the xmlToObject with the array of the byte	
				xmlToObject.nfeMapping(contentFile);
				
				
			} catch (Exception e) {
				
				LOG.info("Erro ao ler o arquivo"+ e.getMessage());
				
			}
			
		}
	} // close method processa()
	

public void processaII(DirectoryStream<Path> arquivos) {
		
		for (Path arquivo : arquivos) {
			
			try {
				
				// convert o arquivo em tipo Byte
				byte[] contentFile = Files.readAllBytes(arquivo.toAbsolutePath());
				
				String xml = new String(contentFile);
		    	JSONObject obj = XML.toJSONObject(xml);
		    	System.out.println(obj);
				
				
								
			} catch (Exception e) {
				
				System.out.println("Erro o ler o arquivo: " +arquivo.getFileName());
			}
			
		}
	} // close method processa()
	

}
