package com.nanoTestes.xml.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TB_PARKING_SPOT")
public class DocModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique=true, length =50)
	String keyNfe;
	
	@Column(nullable = false, unique=true, length =10)
	String cNF;
	
	@Column(nullable = false, unique=true, length =10)
	String nNF;
	
	@Column(nullable = false, unique=true, length =30)
	String dhEmi;
	
	
	
	
	
	
}
