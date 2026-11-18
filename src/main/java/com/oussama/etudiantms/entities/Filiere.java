package com.oussama.etudiantms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
@Table(name = "filieres", schema = "public")
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nom_filiere", nullable = false, unique = true)
    private String nomFiliere;


    // Côté "One" : mappedBy = nom du champ dans Etudiant

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "filiere")
    @Builder.Default
    private List<Etudiant> etudiants=new ArrayList<>();
}
