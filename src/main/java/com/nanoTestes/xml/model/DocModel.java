
package com.nanoTestes.xml.model;



import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="NfeAnalitcs")
public class DocModel {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	// key of document <tag
	@Column(nullable = false, unique=true, length =50)
	String keyNfe;
	
	@Column(nullable = false, unique=false, length =10)
	String cNF;
	
	@Column(nullable = false, unique=false, length =10)
	String nNF;
	
	@Column(nullable = false, unique=false, length =30)
	String dhEmi;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="id_doc_kake")
    private DocLake docKake;

	
	public DocModel(String keyNfe, String nNF) {
		super();
		this.keyNfe = keyNfe;
		this.nNF = nNF;
	}
	
	public DocModel(String keyNfe, String cNF, String nNF, String dhEmi) {
		super();
		this.keyNfe = keyNfe;
		this.cNF = cNF;
		this.nNF = nNF;
		this.dhEmi = dhEmi;
	}
	public DocModel() {
		// TODO Auto-generated constructor stub
	}
	// Métodos Getters and Setter
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKeyNfe() {
		return keyNfe;
	}
	public void setKeyNfe(String keyNfe) {
		this.keyNfe = keyNfe;
	}
	public String getcNF() {
		return cNF;
	}
	public void setcNF(String cNF) {
		this.cNF = cNF;
	}
	public String getnNF() {
		return nNF;
	}
	public void setnNF(String nNF) {
		this.nNF = nNF;
	}
	public String getDhEmi() {
		return dhEmi;
	}
	public void setDhEmi(String dhEmi) {
		this.dhEmi = dhEmi;
	}
	@Override
	public int hashCode() {
		return Objects.hash(cNF, dhEmi, id, keyNfe, nNF);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocModel other = (DocModel) obj;
		return Objects.equals(cNF, other.cNF) && Objects.equals(dhEmi, other.dhEmi) && Objects.equals(id, other.id)
				&& Objects.equals(keyNfe, other.keyNfe) && Objects.equals(nNF, other.nNF);
	}
	@Override
	public String toString() {
		return "DocModel [id=" + id + ", keyNfe=" + keyNfe + ", cNF=" + cNF + ", nNF=" + nNF + ", dhEmi=" + dhEmi + "]";
	}
	
	
	
	
	
	
}
