package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Inscription;
import java.util.List;

public interface InscriptionService {
    Inscription createInscription(int userId, int eventId);
    int getNombreInscriptions(int eventId);
    List<Inscription> getUserInscriptions(int userId);
    List<Inscription> getEventInscriptions(int eventId);
    void deleteInscription(int inscriptionId);
}