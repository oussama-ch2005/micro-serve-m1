package com.oussama.etudiantms;

import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.entities.Etudiant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class EtudiantMsApplication {

    public static void main(String[] args) {SpringApplication.run(EtudiantMsApplication.class, args);}

    @Bean
    public CommandLineRunner init(EtudiantRepository repository) {
        return args -> {
            Etudiant e1 = Etudiant.builder()
                    .id(UUID.randomUUID().toString())
                    .nom("chrif").prenom("oussama").age(20).email("chrifoussama05@test.com").build();
            repository.save(e1);

            Etudiant e2=Etudiant.builder()
                    .id(UUID.randomUUID().toString()).nom("idhika").prenom("achraf").email("idhikaachraf15@test.com").age(30).build();
            repository.save(e2);
        };
    }
    }


