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
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_promotion;
    private float pourcentage_remise;
    private Date date_fin;
    private Date date_debut;
    @OneToMany(mappedBy = "promotion")
    private List<Abonnement> abonnements;
    @ManyToMany(mappedBy = "promotions")
    private List<User> users;
}
