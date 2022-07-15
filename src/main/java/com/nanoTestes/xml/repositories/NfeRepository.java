package com.nanoTestes.xml.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.nanoTestes.xml.model.DocModel;


public interface NfeRepository extends JpaRepository<DocModel, UUID> {
		
	//boolean existsBykeyNfe(String key_nfe);

	boolean existsBynfeKey(DocModel xmlKey);

		
}
