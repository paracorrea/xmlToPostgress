package com.nanoTestes.xml.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Entity(name = "DocLake")
@Table(name = "doc_lake")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class DocLake {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private String jsonbContent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJsonbContent() {
		return jsonbContent;
	}

	public void setJsonbContent(String jsonbContent) {
		this.jsonbContent = jsonbContent;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DocLake other = (DocLake) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
