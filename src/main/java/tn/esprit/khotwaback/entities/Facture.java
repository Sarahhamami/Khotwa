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
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_facture;
    private Date date_facture;
    @ManyToOne
    private Abonnement abonnement;
    @OneToMany(mappedBy = "facture")
    private List<Paiement> paiement;

}
