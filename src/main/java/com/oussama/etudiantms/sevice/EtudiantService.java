package com.oussama.etudiantms.sevice;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.entities.Etudiant;
import com.oussama.etudiantms.mapper.InterfaceMapper;

import java.util.*;

@org.springframework.stereotype.Service

public class EtudiantService implements InterfaceService{
    EtudiantRepository etudiantRepository;
    InterfaceMapper interfaceMapper;


    public EtudiantService(EtudiantRepository etudiantRepository, InterfaceMapper interfaceMapper) {
        this.etudiantRepository = etudiantRepository;
        this.interfaceMapper = interfaceMapper;
    }


    



    @Override
    public EtudiantResponseDto getEtudiantById(String id) {
        Etudiant e=etudiantRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Utilisateur Introuvable"));;
        return interfaceMapper.EtudiantToEtudiantResonseDto(e);



    }

    @Override
    public EtudiantResponseDto getEtudiantByName(String nom) {
        Etudiant e =etudiantRepository.findByNom(nom)
                .orElseThrow(()-> new RuntimeException("Utilisateur Introuvable"));
        return interfaceMapper.EtudiantToEtudiantResonseDto(e);



    }

    @Override
    public List<EtudiantResponseDto> getEtudiants() {

        List<Etudiant> e=etudiantRepository.findAll();
        List<EtudiantResponseDto> e_dto=new ArrayList<>();
        for (Etudiant e1:e){
            e_dto.add(interfaceMapper.EtudiantToEtudiantResonseDto(e1));

        }
        return e_dto ;
    }

    @Override
    public void createEtudiant(EtudiantRequestDto e) {
        Etudiant x=new Etudiant();
        x.setNom(e.getNom());
        x.setPrenom(e.getPrenom());
        x.setAge(e.getAge());


        Random rand = new Random();
        int nombreAleatoire = rand.nextInt(90) + 10;

        if(e.getNom()!=null && e.getPrenom()!=null){
            x.setEmail(e.getNom()+e.getPrenom()+nombreAleatoire  +"@test.com");
        }
        x.setId(UUID.randomUUID().toString());
        etudiantRepository.save(x);


    }

    @Override
    public void updateEtudiant(String id,EtudiantRequestDto etudiant) {
         Etudiant e =etudiantRepository.findById(id)
                 .orElseThrow(()-> new RuntimeException("Utilisateur Introuvable"));
        if (e.getNom() != null) {
            etudiant.setNom(e.getNom());
        }
        if (e.getAge() != null) {
            etudiant.setAge(e.getAge());}

        if (e.getPrenom() != null) {
            etudiant.setPrenom(e.getPrenom());
        }


        etudiantRepository.save(e);




    }

    @Override
    public void deleteEtudiant(String id) {
        etudiantRepository.deleteById(id);

    }
}
