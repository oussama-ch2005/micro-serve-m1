package com.oussama.etudiantms;

import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.Repositories.FiliereRepository;
import com.oussama.etudiantms.Repositories.ModuleRepository;
import com.oussama.etudiantms.entities.CarteEtudiant;
import com.oussama.etudiantms.entities.Etudiant;
import com.oussama.etudiantms.entities.Filiere;
import com.oussama.etudiantms.entities.Module;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EtudiantMsApplication {

    public static void main(String[] args) {SpringApplication.run(EtudiantMsApplication.class, args);}

    @Bean
    public CommandLineRunner init(EtudiantRepository etudiantRepository, ModuleRepository moduleRepository, FiliereRepository filiereRepository) {

        return args -> {

            if (filiereRepository.count() > 0) return;


            Filiere idld = filiereRepository.save(Filiere.builder().nomFiliere("IDLD").build());
            Filiere gl   = filiereRepository.save(Filiere.builder().nomFiliere("Genie logiciel").build());


            Module m1 = moduleRepository.save(Module.builder().nomModule("Systeme distribue").build());
            Module m2 = moduleRepository.save(Module.builder().nomModule("Algorithme avancee").build());

            Etudiant e1 = Etudiant.builder()
                    .nom("chrif").prenom("oussama").age(20)
                    .email("chrifoussama05@test.com")
                    .filiere(idld)
                    .carte(CarteEtudiant.builder().numeroCarte("CE-0001").build())
                    .build();
            e1.addModule(m1);
            e1.addModule(m2);
            etudiantRepository.save(e1);

            Etudiant e2 = Etudiant.builder()
                    .nom("idhika").prenom("achraf").age(21)
                    .email("idhikaachraf15@test.com")
                    .filiere(idld)
                    .carte(CarteEtudiant.builder().numeroCarte("CE-0002").build())
                    .build();
            e2.addModule(m1);
            etudiantRepository.save(e2);

            Etudiant e3 = Etudiant.builder()
                    .nom("hassan").prenom("bourika").age(20)
                    .email("bourikahasan22@test.com")
                    .filiere(gl)
                    .carte(CarteEtudiant.builder().numeroCarte("CE-0003").build())
                    .build();
            etudiantRepository.save(e3);


        };

    }
}


