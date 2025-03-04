package tn.esprit.khotwaback.entities;

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
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_facture;
    private Date date_facture;
    @ManyToOne
    @JoinColumn(name = "abonnement_id", nullable = false)
    private Abonnement abonnement;
    @OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paiement> paiements;

}
