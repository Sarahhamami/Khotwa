package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user ;
    private String nom;
    private String prenom;
    private String email ;
    private String mdp ;
    private ROLE role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Abonnement> abonnements;
    @OneToMany(mappedBy = "user")
    private List<Paiement> paiements;
    @ManyToMany
    private List<Promotion> promotions;

}
