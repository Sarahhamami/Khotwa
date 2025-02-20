package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Entity
@ToString
@Setter
@Getter
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_evenement;
    private String titre;
    private String description;
    private Date date;
    private String lieu;
    @ManyToMany(mappedBy = "evenements")
    private List<User> users;
    @OneToMany(mappedBy = "evenement")
    private List<Certificat_evenement> certificatEvenements;
    @OneToMany(mappedBy = "evenement")
    private List<Commentaire_evenement> commentaireEvenements;

}
