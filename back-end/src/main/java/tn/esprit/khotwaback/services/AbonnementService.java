package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.PLAN_abonnement;

import java.util.List;
import java.util.Map;

public interface AbonnementService {
    Abonnement addAbonnement(Abonnement abonnement);
    Abonnement updateAbonnement( Abonnement abonnement);
    void deleteAbonnement(int id);
    Abonnement getAbonnementById(int id);
    List<Abonnement> getAllAbonnements();
    List<Abonnement> getAbonnementsByPlan(PLAN_abonnement plan);
    public List<Abonnement> getAbonnementsSortedByPrice(String sortDirection, PLAN_abonnement plan);
    Map<String, Object> getAbonnementStatistics();

}
