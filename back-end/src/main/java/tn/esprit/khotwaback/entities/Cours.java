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
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cours;
    private String titre;
    private String description ;
    @ManyToMany(mappedBy = "cours")
    private List<User> users;
    @OneToMany(mappedBy = "cours")
    private List<Certificat_cours> certificatCours;
    @OneToMany(mappedBy = "cours")
    private List<Commentaire_cours> commentaireCours;
    @OneToMany(mappedBy = "cours")
    private List<Contenu> contenus;
    @OneToMany(mappedBy = "cours")
    private List<Quizz> quizzes;
}
