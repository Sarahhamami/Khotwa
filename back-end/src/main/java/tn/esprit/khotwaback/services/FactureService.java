package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Facture;
import java.util.List;

public interface FactureService {
    Facture addFacture(Facture facture);
    Facture getFactureById(int id);
    List<Facture> getAllFactures();
    Facture updateFacture( Facture facture);
    void deleteFacture(int id);
}
