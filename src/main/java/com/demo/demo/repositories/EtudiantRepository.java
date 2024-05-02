package com.demo.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.demo.demo.entities.Etudiant;

public interface EtudiantRepository extends CrudRepository<Etudiant, Integer> {

    Etudiant findByNom(String string);
}
