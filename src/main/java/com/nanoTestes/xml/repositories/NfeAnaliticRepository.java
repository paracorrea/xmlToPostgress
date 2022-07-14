package com.nanoTestes.xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nanoTestes.xml.model.DocModel;

public interface NfeAnaliticRepository extends JpaRepository<DocModel, Long> {

}
