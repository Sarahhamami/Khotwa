package tn.esprit.khotwaback.dtos;

import lombok.Data;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.Paiement;

import java.util.Date;
import java.util.List;
@Data
public class FactureDTO {
    private int id_facture;
    private Date date_facture;
    private Abonnement abonnement;
    private List<Paiement> paiements;
}
