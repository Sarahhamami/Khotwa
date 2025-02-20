package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Entity
@ToString
@Setter
@Getter
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_paiement;
    private float montant;
    private String devise;
    private String modePaiement;
    private Date date_paiement;
    @ManyToOne
    private User user;
    @ManyToOne
    private Facture facture;


}
