package tn.esprit.khotwaback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.*;
import tn.esprit.khotwaback.repositories.InscriptionRepository;
import tn.esprit.khotwaback.repositories.UserRepository;
import tn.esprit.khotwaback.repositories.EvenementRepository;
import tn.esprit.khotwaback.services.InscriptionService;

import java.util.List;

@Service
public class InscriptionServiceImpl implements InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Inscription createInscription(int userId, int eventId) {
        User user = userRepository.findById(userId).orElse(null);
        Evenement event = evenementRepository.findById(eventId).orElse(null);

        if(user == null || event == null) {
            return null;
        }

        // Vérifier la capacité
        if(inscriptionRepository.countByEvenement(event) >= event.getCapacite()) {
            return null;
        }

        Inscription inscription = new Inscription();
        inscription.setUser(user);
        inscription.setEvenement(event);

        return inscriptionRepository.save(inscription);
    }

    @Override
    public int getNombreInscriptions(int eventId) {
        return inscriptionRepository.countByEvenementEventId(eventId);
    }

    @Override
    public List<Inscription> getUserInscriptions(int userId) {
        return inscriptionRepository.findByUserId(userId);
    }

    @Override
    public List<Inscription> getEventInscriptions(int eventId) {
        return inscriptionRepository.findByEvenementEventId(eventId);
    }

    @Override
    public void deleteInscription(int inscriptionId) {
        inscriptionRepository.deleteById(inscriptionId);
    }
}