package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Paiement;


import java.util.List;

public interface PaiementService {
    Paiement addPaiement(Paiement paiement);
    Paiement updatePaiement( Paiement paiement);
    void deletePaiement(int id);
    Paiement getPaiementById(int id);
    List<Paiement> getAllPaiements();
}
