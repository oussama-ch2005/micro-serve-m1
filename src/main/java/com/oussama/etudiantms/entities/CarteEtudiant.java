package com.oussama.etudiantms.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cartes_etudiant", schema = "public")
public class CarteEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String numeroCarte;
}