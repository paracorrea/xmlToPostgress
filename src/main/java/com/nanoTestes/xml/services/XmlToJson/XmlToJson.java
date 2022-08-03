package com.nanoTestes.xml.services.XmlToJson;

 



import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONObject;
import org.json.XML;




public class XmlToJson {
	
	Path xmlFile;
	

     public void xmlConvert (String arquivo) throws Exception {
        
    	     	 
    	 byte[] b = Files.readAllBytes(xmlFile.toAbsolutePath());
    	 
    	 String xml = new String(b);
    	 
    	 JSONObject obj = XML.toJSONObject(xml);
    	 
    	 System.out.println(obj);
    	 
    }
}