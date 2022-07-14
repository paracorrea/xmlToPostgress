package com.nanoTestes.xml.services.xmlToObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.transaction.Transactional;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.nanoTestes.xml.model.DocModel;
import com.nanoTestes.xml.repositories.NfeAnaliticRepository;

@Service
public class XmlToObject {

	@Autowired
	final NfeAnaliticRepository nfeAnaliticsRepository;
	
	public XmlToObject(NfeAnaliticRepository nfeAnaliticsRepository) {
		this.nfeAnaliticsRepository = nfeAnaliticsRepository;
	}
	
	@Autowired
	@Transactional
	public DocModel save(DocModel docModel) {
		return nfeAnaliticsRepository.save(docModel);
	}
	
	//Document doc = null;
	XPath xPath = null;
	String keyNfe;
	String nNF;
	
	/*
	 * this method return a String or resolve the programming 
	 * reciving a type byte array and transform in a XML java document 
	 * follow get your content for future 
	 */
	public String nfeMapping(byte[] file) {
		
		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( new ByteArrayInputStream(file));
			
			xPath = XPathFactory.newInstance().newXPath();
			
			keyNfe = xPath.compile("/nfeProc/NFe/infNFe/@Id").evaluate(doc);
			nNF = xPath.compile("/nfeProc/NFe/infNFe/ide/nNF").evaluate(doc);
			
			
			var docModel = new DocModel();
			docModel.setKeyNfe(keyNfe);
			docModel.setnNF(nNF);
			
			save(docModel);
			
			
			System.out.println(keyNfe+ " "+nNF);
			
		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
