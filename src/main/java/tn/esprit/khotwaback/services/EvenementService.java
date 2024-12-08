package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Evenement;
import tn.esprit.khotwaback.entities.Status_evenement;

import java.util.List;

public interface EvenementService {
    Evenement createEvenement(Evenement evenement);
    Evenement updateEvenement(int eventId, Evenement evenement);
    void deleteEvenement(int eventId);
    List<Evenement> getAllEvenements();
    Evenement getEvenementById(int eventId);
    byte[] generateQRCode(int eventId, int width, int height) throws Exception;

    List<Evenement> findByStatus(Status_evenement status);


}