package com.oussama.etudiantms.DTOs;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantRequestDto {
    public String nom;
    public String prenom;
    public Integer age;
}
