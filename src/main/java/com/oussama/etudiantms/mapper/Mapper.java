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
    public EtudiantResponseDto EtudiantToEtudiantResonseDto(Etudiant etudiant) {
        EtudiantResponseDto x=new EtudiantResponseDto();
        BeanUtils.copyProperties(etudiant,x);
        return x;

    }



    @Override
    public Etudiant etudiantRequestDtoToEtudiant(EtudiantRequestDto e) {
        Etudiant x =new Etudiant();
        BeanUtils.copyProperties(e, x);
        x.setId(UUID.randomUUID().toString());
        return x;
    }
}
