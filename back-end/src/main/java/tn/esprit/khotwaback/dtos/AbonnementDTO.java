package tn.esprit.khotwaback.dtos;


import lombok.Data;
import tn.esprit.khotwaback.entities.Facture;
import tn.esprit.khotwaback.entities.PLAN_abonnement;
import tn.esprit.khotwaback.entities.Promotion;
import tn.esprit.khotwaback.entities.User;

import java.util.Date;
import java.util.List;

@Data
public class AbonnementDTO {
    private int id_abonnement;
    private Date date_debut;
    private Date date_fin;
    private PLAN_abonnement plan;
    private float prix;
    private User user;
    private Promotion promotion;
    private List<Facture> factures;
}
