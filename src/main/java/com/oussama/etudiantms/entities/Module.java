package com.oussama.etudiantms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Modules",schema = "public")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_Module",nullable = false, unique = true)
    private String nomModule;

    // Côté inverse : mappedBy = nom du champ dans Etudiant
    @Builder.Default
    @ManyToMany(mappedBy = "modules")
    List<Etudiant> etudiants=new ArrayList<>();


}
