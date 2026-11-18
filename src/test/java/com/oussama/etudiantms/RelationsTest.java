package com.oussama.etudiantms;

import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.Repositories.FiliereRepository;
import com.oussama.etudiantms.Repositories.ModuleRepository;
import com.oussama.etudiantms.entities.CarteEtudiant;
import com.oussama.etudiantms.entities.Etudiant;
import com.oussama.etudiantms.entities.Filiere;
import com.oussama.etudiantms.entities.Module;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class RelationsTest {
    @Autowired
    EtudiantRepository etudiantRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    FiliereRepository filiereRepository;
    @Autowired
    EntityManager em;



    @Test
    @Transactional
        // annulé (rollback) à la fin du test
    void lesRelationsFonctionnent() {
        Filiere f = filiereRepository.save(Filiere.builder().nomFiliere("TEST").build());
        Module m = moduleRepository.save(Module.builder().nomModule("Module test").build());

        Etudiant e = Etudiant.builder().nom("test").prenom("junit").age(20)
                .filiere(f)
                .carte(CarteEtudiant.builder().numeroCarte("CE-TEST").build())
                .build();
        e.addModule(m);
        etudiantRepository.save(e);

        em.flush();   // écrit en base
        em.clear();   // vide le cache : les lectures suivantes viennent de la base

        Etudiant relu = etudiantRepository.findById(e.getId()).orElseThrow();
        assertEquals("TEST", relu.getFiliere().getNomFiliere());      // ManyToOne
        assertEquals(1, relu.getModules().size());                    // ManyToMany
        assertEquals("CE-TEST", relu.getCarte().getNumeroCarte());    // OneToOne

        Module moduleRelu = moduleRepository.findById(m.getId()).orElseThrow();
        assertEquals(1, moduleRelu.getEtudiants().size());            // côté inverse
    }

}
