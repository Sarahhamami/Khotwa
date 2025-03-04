package tn.esprit.khotwaback.dtos;


import lombok.Data;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.User;

import java.util.Date;
import java.util.List;
@Data
public class PromotionDTO {
    private int id_promotion;
    private float pourcentage_remise;
    private Date date_fin;
    private Date date_debut;
    private List<Abonnement> abonnements;
    private List<User> users;
}
