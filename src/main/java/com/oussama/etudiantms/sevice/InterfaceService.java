package com.oussama.etudiantms.sevice;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.entities.Etudiant;
import org.springframework.stereotype.Service;

import java.util.List;

public interface InterfaceService {
    public EtudiantResponseDto getEtudiantById(String id);
    public  EtudiantResponseDto getEtudiantByName(String nom);
    public List<EtudiantResponseDto> getEtudiants();
    public void createEtudiant(EtudiantRequestDto e);
    public void updateEtudiant(String id,EtudiantRequestDto e);
    public void deleteEtudiant(String id);

}
