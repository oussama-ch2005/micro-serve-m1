package com.oussama.etudiantms.DTOs;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EtudiantResponseDto {
    public String nom;
    public String prenom;
    public Integer age;

}
