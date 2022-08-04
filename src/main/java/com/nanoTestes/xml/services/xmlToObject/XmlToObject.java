package com.nanoTestes.xml.services.xmlToObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.nanoTestes.xml.model.DocModel;
import com.nanoTestes.xml.repositories.NfeRepository;
import com.nanoTestes.xml.services.DocService;



@Service
public class XmlToObject {

	
	static final Logger LOG = LogManager.getLogger(DocService.class.getName());
	
	@Autowired
	final NfeRepository nfeRepository;
	
	
	public XmlToObject(NfeRepository nfeRepository) {
		
		this.nfeRepository = nfeRepository;
	}
	
	public boolean existsByKeyNfe(String KeyNfe) {
		
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
	public String nfeMapping(byte[] nameFile) {		
		try {
			Document docXml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( new ByteArrayInputStream(nameFile));
			
			
			xPath = XPathFactory.newInstance().newXPath();
			
			//Element htmlTag = (Element) doc.getDocumentElement().getFirstChild();
			Element htmlTag = (Element) docXml.getDocumentElement().getChildNodes().item(0);
			
			
			
					
			System.out.println("Elemtento da Tag: "+htmlTag.toString().substring(1, 4));
			
			
			String substringOfXml = htmlTag.toString().substring(1, 4);
			
			
			if (substringOfXml.equals("NFe")) {
				
				_keyNfe = xPath.compile("/nfeProc/NFe/infNFe/@Id").evaluate(docXml); // for NF-e
				_nNF = xPath.compile("/nfeProc/NFe/infNFe/ide/nNF").evaluate(docXml);
				_cNF = xPath.compile("/nfeProc/NFe/infNFe/ide/cNF").evaluate(docXml);
				_dhEmi = xPath.compile("/nfeProc/NFe/infNFe/ide/dhEmi").evaluate(docXml);
				
				LOG.info("Foram encontradas notas fiscais do tipo NFe");
				
				DocModel docModel = new DocModel();
				docModel.setKeyNfe(_keyNfe);
				docModel.setnNF(_nNF);
				docModel.setcNF(_cNF);
				docModel.setDhEmi(_dhEmi);
				  
				  if (existsByKeyNfe(docModel.getKeyNfe()) || (_keyNfe.isEmpty())) {// if keyNf exist in database not save 
					  
					  
					 LOG.info("Nota fiscal: " +docModel.getKeyNfe()+ " já existente no banco de dados ou está em branco");
					 
				  } else {
					  
					  LOG.info("Salvando no banco de dados a NF  " +docModel.getKeyNfe()); 
					  nfeRepository.save(docModel);
				  }
				
			} else if (substringOfXml.equals("CTe") ) {
			
				_keyNfe = xPath.compile("/cteProc/CTe/infCte/@Id").evaluate(docXml); // for NF-e
				_nNF = xPath.compile("/cteProc/CTe/infCte/ide/nCT").evaluate(docXml);
				_cNF = xPath.compile("/cteProc/CTe/infCte/ide/cCT").evaluate(docXml);
				_dhEmi = xPath.compile("/cteProc/CTe/infCte/ide/dhEmi").evaluate(docXml);
				
				LOG.info("Passou aqui if CTe");
				
				DocModel docModel = new DocModel();
				docModel.setKeyNfe(_keyNfe);
				docModel.setnNF(_nNF);
				docModel.setcNF(_cNF);
				docModel.setDhEmi(_dhEmi);
				
				  
				  if (existsByKeyNfe(docModel.getKeyNfe()) || (_keyNfe.isEmpty())) {// if keyNf exist in database not save 
					  
					LOG.info("Nota fiscal: " +docModel.getKeyNfe()+ " já existente no banco de dados ou está em branco");
					 
				  } else {
					  
					  LOG.info("Salvando no banco de dados a NF  " +docModel.getKeyNfe()); 
					  nfeRepository.save(docModel);
				  }

			} else if ( substringOfXml != "NFe" || substringOfXml != "CTe") {
				
				_keyNfe = "";
				_nNF = "";
				_cNF = "";
				_dhEmi = "";
				
				DocModel docModel = new DocModel();
				docModel.setKeyNfe(_keyNfe);
				docModel.setnNF(_nNF);
				docModel.setcNF(_cNF);
				docModel.setDhEmi(_dhEmi);
				System.out.println("Passou aqui if NFe-s");
				
				  
				  if (existsByKeyNfe(docModel.getKeyNfe()) || (_keyNfe.isEmpty())) {// if keyNf exist in database not save 
					  
					LOG.info("Nota fiscal: " +docModel.getKeyNfe()+ " já existente no banco de dados ou está em branco");
					 
				  } else {
					  
					  System.out.println("Salvando no banco de dados a NF  " +docModel.getKeyNfe()); 
					  nfeRepository.save(docModel);
				  }
				
			}
				
 
			
		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return null;
		
	} // fecha o método nfeMapping
	
	public void printXpathResult(Object result) { 
		   NodeList nodes = (NodeList) result; 
		   for (int i = 0; i < nodes.getLength(); i++) { 
		   System.out.println(nodes.item(i).getNodeValue()); 
		   } 
		}
		 

} // fecha a classe
