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
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_abonnement;
    private Date date_debut;
    private Date date_fin;
    private PLAN_abonnement plan;
    private float prix;
    @ManyToOne
    private User user;
    @ManyToOne
    private Promotion promotion;
    @OneToMany(mappedBy = "abonnement")
    private List<Facture> factures;
}
