package com.oussama.etudiantms.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "etudiants", schema = "public")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nom;
    private String prenom;
    private Integer age;
    private String email;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "filiere_id")
    private Filiere filiere;



    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="etudiant_cours",
            joinColumns = @JoinColumn(name="etudiant_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    @Builder.Default
    private List<Module> modules=new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "carte_id", unique = true)//collone de relation
    private CarteEtudiant carte;

    public void addModule(Module m) {
        modules.add(m);
        m.getEtudiants().add(this);
    }

    public void removeModule(Module m) {
        modules.remove(m);
        m.getEtudiants().remove(this);
    }
}
