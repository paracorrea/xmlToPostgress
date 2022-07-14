package com.nanoTestes.xml.services.xmlToObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

@Service
public class XmlToObject {

	Document doc = null;
	XPath xPath = null;
	String keyNfe;
	String numNfe;
	
	public String nfeMapping(byte[] file) {
		
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( new ByteArrayInputStream(file));
			
			xPath = XPathFactory.newInstance().newXPath();
			
			keyNfe = xPath.compile("/nfeProc/NFe/infNFe/@Id").evaluate(doc);
			numNfe = xPath.compile("/nfeProc/NFe/infNFe/ide/nNF").evaluate(doc);
			
			
			System.out.println(keyNfe+ " "+numNfe);
			
		} catch (SAXException | IOException | ParserConfigurationException | XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
}
