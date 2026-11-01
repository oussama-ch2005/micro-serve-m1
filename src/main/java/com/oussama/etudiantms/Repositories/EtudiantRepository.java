package com.oussama.etudiantms.Repositories;

import com.oussama.etudiantms.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface EtudiantRepository extends JpaRepository<Etudiant, String> {

    Optional<Etudiant> findByNom(String nom);
}
