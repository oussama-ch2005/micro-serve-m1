package com.oussama.etudiantms.DTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EtudiantResponseDto {
    private String id;
    private String nom;
    private String prenom;
    private Integer age;
    private String email;
    private String filiere;
    private String numeroCarte;
    private List<String> modules;

}
