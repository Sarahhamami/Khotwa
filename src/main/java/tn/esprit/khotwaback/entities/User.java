package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@ToString
@Setter
@Getter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String email;
    private String mdp;

    @Enumerated(EnumType.STRING)  // Added to properly handle the enum
    private ROLE role;

    @ManyToMany
    private List<Evenement> evenements;

    @OneToMany(mappedBy = "user")
    private List<Inscription> inscriptions;

    @OneToMany(mappedBy = "user")
    private List<Certificat_evenement> certificatEvenements;

    @OneToMany(mappedBy = "user")
    private List<Commentaire_evenement> commentaireEvenements;
}