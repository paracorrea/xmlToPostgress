package com.nanoTestes.xml.services.xmlToObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nanoTestes.xml.model.DocModel;
import com.nanoTestes.xml.repositories.NfeRepository;



@Service
public class XmlToObject {

	@Autowired
	final NfeRepository nfeRepository;
	
	
	public XmlToObject(NfeRepository nfeRepository) {
		
		this.nfeRepository = nfeRepository;
	}
	
	public boolean existsByKeyNfe(String KeyNfe) {
		// metodo declarado no Repository
		return nfeRepository.existsByKeyNfe(KeyNfe);
	}
	
	//Document doc = null;
	XPath xPath = null;
	String _keyNfe;
	String _nNF;
	String _cNF;
	String _dhEmi;
	String identifyNf;
	
	//DocModel docModel = new DocModel();
	/*KeyNfe
	 * this method return a String or resolve the programming 
	 * reciving a type byte array and transform in a XML java document 
	 * follow get your content for future 
	 */
	public String nfeMapping(byte[] file) {
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( new ByteArrayInputStream(file));
			
			
			xPath = XPathFactory.newInstance().newXPath();
			
			_keyNfe = xPath.compile("/nfeProc/NFe/infNFe/@Id").evaluate(doc);
						
			// if the keynf is empty not save repository 
			if (!_keyNfe.isEmpty()) {
			
			
				
			_nNF = xPath.compile("/nfeProc/NFe/infNFe/ide/nNF").evaluate(doc);
			_cNF = xPath.compile("/nfeProc/NFe/infNFe/ide/cNF").evaluate(doc);
			_dhEmi = xPath.compile("/nfeProc/NFe/infNFe/ide/dhEmi").evaluate(doc);
			
			
			
			 
			  DocModel docModel = new DocModel();
			  docModel.setKeyNfe(_keyNfe);
			  docModel.setnNF(_nNF);
			  docModel.setcNF(_cNF);
			  docModel.setDhEmi(_dhEmi);
			  
			  // if keyNf exist in database not save 
			  if (existsByKeyNfe(docModel.getKeyNfe())) {
				  
				 System.out.println("Nofa fiscal já existente");
				 
			  } else {
			  
				  nfeRepository.save(docModel);
			  			
				  System.out.println("Chave: "+docModel.getKeyNfe()+ " Número d Nota: "+ docModel.getnNF());	
			  }	
		} // fecha if que verifica se o campo ChaveNf está vazio
			
		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
		
	} // fecha o método nfeMapping
	
	 

} // fecha a classe
