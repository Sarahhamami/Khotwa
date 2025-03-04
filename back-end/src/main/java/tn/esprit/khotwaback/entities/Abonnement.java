package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_abonnement;
    private Date date_debut;
    private Date date_fin;
    @Enumerated(EnumType.STRING)
    private PLAN_abonnement plan;
    private float prix;
    @ManyToOne
    @JsonBackReference
    private User user;
    @ManyToOne
    @JsonBackReference(value = "promo-abonnement")
    private Promotion promotion;
    @OneToMany(mappedBy = "abonnement")
    private List<Facture> factures;



}
