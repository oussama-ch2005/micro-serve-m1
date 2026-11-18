package com.oussama.etudiantms.sevice;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.entities.Etudiant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InterfaceService {
     EtudiantResponseDto getEtudiantById(String id);
     EtudiantResponseDto getEtudiantByName(String nom);
     List<EtudiantResponseDto> getEtudiants();
    EtudiantResponseDto createEtudiant(EtudiantRequestDto e);
    EtudiantResponseDto updateEtudiant(String id,EtudiantRequestDto e);
    void deleteEtudiant(String id);
    EtudiantResponseDto inscrireModule(String etudiantId, Long moduleId);
    EtudiantResponseDto retirerModule(String etudiantId, Long moduleId);
}


