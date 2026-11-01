package com.oussama.etudiantms.mapper;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.entities.Etudiant;

public interface InterfaceMapper {

    public EtudiantResponseDto EtudiantToEtudiantResonseDto(Etudiant etudiant);

    public Etudiant etudiantRequestDtoToEtudiant(EtudiantRequestDto etudiantResponseDto);

}
