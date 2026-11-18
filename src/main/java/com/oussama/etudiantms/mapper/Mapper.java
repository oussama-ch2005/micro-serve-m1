package com.oussama.etudiantms.mapper;

import com.oussama.etudiantms.DTOs.EtudiantRequestDto;
import com.oussama.etudiantms.DTOs.EtudiantResponseDto;
import com.oussama.etudiantms.entities.Etudiant;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component

public class Mapper implements InterfaceMapper {


    @Override
    public EtudiantResponseDto EtudiantToEtudiantResonseDto(Etudiant e) {
        return EtudiantResponseDto.builder()
                .id(e.getId())
                .nom(e.getNom())
                .prenom(e.getPrenom())
                .age(e.getAge())
                .email(e.getEmail())
                .filiere(e.getFiliere() != null ? e.getFiliere().getNomFiliere() : null)
                .numeroCarte(e.getCarte() != null ? e.getCarte().getNumeroCarte() : null)
                .modules(e.getModules().stream().map(m -> m.getNomModule()).toList())
                .build();

    }



    @Override
    public Etudiant etudiantRequestDtoToEtudiant(EtudiantRequestDto e) {
        Etudiant x =new Etudiant();
        BeanUtils.copyProperties(e, x);

        return x;
    }
}
