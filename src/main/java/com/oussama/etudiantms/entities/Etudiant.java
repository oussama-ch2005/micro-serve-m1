package com.oussama.etudiantms.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.*;

@Entity
@Getter @Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {
    @Id
    private String id;
    private String nom;
   private  String prenom;
    private Integer age;
   private String email;
}
