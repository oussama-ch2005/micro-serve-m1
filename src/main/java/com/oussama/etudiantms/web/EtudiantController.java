package com.oussama.etudiantms.web;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.Repositories.EtudiantRepository;
import com.oussama.etudiantms.entities.Etudiant;
import com.oussama.etudiantms.sevice.InterfaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EtudiantController {

    InterfaceService interfaceService;

    // ICI IoC par ID par constructeur
    public EtudiantController(InterfaceService interfaceService) {
        this.interfaceService = interfaceService;
    }


  //  @GetMapping("etudiant/{nom]")
    //public EtudiantResponseDto etudiantNom(@PathVariable  String nom){
      //  return interfaceService.getEtudiantByName(nom);
    //}


    // GET DE HTTP:
    // L'appel se fera:  http://localhost:6060/etudiants
    @GetMapping("/etudiants")
    public List<EtudiantResponseDto> etudiants(){
        return interfaceService.getEtudiants();
    }

    // GET
    // / L'appel se fera:  http://localhost:6060/etudiants/i
    // Si on cherche l'étudiant d'Id i (un entier)
    // i passé dans les paramètres avec @PathVariable
    @GetMapping("/etudiants/{id}")
    public EtudiantResponseDto etudiantId(@PathVariable String id){
        return interfaceService.getEtudiantById(id);
    }

    // POST pour créer un étudiant sur la BD
    // l'appel sera par POST (Postman)
    // http://localhost:6060/etudiants
    // On passe etudiant dans le body de  POST
    @PostMapping("/etudiants")
    public void newEtudiant(@RequestBody EtudiantRequestDto etudiant){
        interfaceService.createEtudiant(etudiant);

    }

    // appel http://localhost:6060/etudiants/2
    // enlève l'étudiant 2 de la base avec DELETTE de HTTP.
    @DeleteMapping ("/etudiants/{id}")
    public void deleteEtudiant(@PathVariable String id){
        try {
            interfaceService.deleteEtudiant(id);
        }catch (RuntimeException e){
            e.printStackTrace();
        }

        // Ici on suppose que l'étudiant id existe on verra comment
        // faire sinon
    }

    // On veut modifier l'etudiant id
    // par l'étudiant  etudiant donné dans le body PUT

    @PutMapping("/etudiants/{id}")
    public void modifieEtudiant (@PathVariable String id, @RequestBody EtudiantRequestDto etudiant){
       try{
           interfaceService.updateEtudiant(id,etudiant);
       }catch (RuntimeException e){
           throw  new RuntimeException(String.format("etudiant %s not found",id));
       }
    }

}
