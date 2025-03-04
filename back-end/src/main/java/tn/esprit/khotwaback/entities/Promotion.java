package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_promotion;
    private float pourcentage_remise;
    private Date date_fin;
    private Date date_debut;
    @OneToMany(mappedBy = "promotion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "promo-abonnement")
    private List<Abonnement> abonnements;
    @ManyToMany(mappedBy = "promotions")
    private List<User> users;
}
