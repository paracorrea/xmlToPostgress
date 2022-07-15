package com.nanoTestes.xml.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nanoTestes.xml.model.DocModel;

@Repository
public interface NfeRepository extends JpaRepository<DocModel, Long> {

}
