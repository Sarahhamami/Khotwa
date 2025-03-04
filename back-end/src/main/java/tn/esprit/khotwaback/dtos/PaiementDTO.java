package tn.esprit.khotwaback.dtos;


import lombok.Data;
import tn.esprit.khotwaback.entities.Facture;
import tn.esprit.khotwaback.entities.User;

import java.util.Date;

@Data
public class PaiementDTO {
    private int id_paiement;
    private float montant;
    private String devise;
    private String modePaiement;
    private Date date_paiement;
    private User user;
    private Facture facture;
}
