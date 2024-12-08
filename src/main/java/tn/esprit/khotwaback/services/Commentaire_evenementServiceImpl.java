package tn.esprit.khotwaback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Commentaire_evenement;
import tn.esprit.khotwaback.repositories.Commentaire_evenementRepository;
import tn.esprit.khotwaback.services.Commentaire_evenementService;

import java.util.List;

@Service
public class Commentaire_evenementServiceImpl implements Commentaire_evenementService {

    @Autowired
    private Commentaire_evenementRepository commentaireEvenementRepository;

    @Override
    public Commentaire_evenement addCommentaire(Commentaire_evenement commentaire) {
        return commentaireEvenementRepository.save(commentaire);
    }

    @Override
    public Commentaire_evenement updateCommentaire(int eventId, Commentaire_evenement commentaire) {
        if (commentaireEvenementRepository.existsById(eventId)) {
            commentaire.setId_commentaire_evenement(eventId);
            return commentaireEvenementRepository.save(commentaire);
        }
        return null;
    }

    @Override
    public void deleteCommentaire(int id) {
        commentaireEvenementRepository.deleteById(id);
    }

    @Override
    public List<Commentaire_evenement> getAllCommentaires() {
        return commentaireEvenementRepository.findAll();
    }

    @Override
    public Commentaire_evenement getCommentaireById(int id) {
        return commentaireEvenementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Commentaire_evenement> getCommentairesByEventId(Integer eventId) {
        return commentaireEvenementRepository.findByEvenementEventId(eventId);
    }

    @Override
    public List<Commentaire_evenement> getCommentairesByUserId(Integer userId) {
        return commentaireEvenementRepository.findByUserId(userId);
    }

    @Override
    public List<Commentaire_evenement> getCommentairesByEventAndUserId(Integer eventId, Integer userId) {
        return commentaireEvenementRepository.findByEvenementAndUser(eventId, userId);
    }
}
