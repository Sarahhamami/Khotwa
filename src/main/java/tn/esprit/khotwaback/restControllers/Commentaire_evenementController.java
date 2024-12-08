package tn.esprit.khotwaback.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Commentaire_evenement;
import tn.esprit.khotwaback.services.Commentaire_evenementService;

import java.util.List;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin(origins = "*")
public class Commentaire_evenementController {

    @Autowired
    private Commentaire_evenementService commentaireEvenementService;

    @PostMapping("/add")
    public Commentaire_evenement addCommentaire(@RequestBody Commentaire_evenement commentaire) {
        return commentaireEvenementService.addCommentaire(commentaire);
    }

    @PutMapping("/update/{id}")
    public Commentaire_evenement updateCommentaire(@PathVariable int id, @RequestBody Commentaire_evenement commentaire) {
        return commentaireEvenementService.updateCommentaire(id, commentaire);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCommentaire(@PathVariable int id) {
        commentaireEvenementService.deleteCommentaire(id);
    }

    @GetMapping("/all")
    public List<Commentaire_evenement> getAllCommentaires() {
        return commentaireEvenementService.getAllCommentaires();
    }

    @GetMapping("/{id}")
    public Commentaire_evenement getCommentaireById(@PathVariable int id) {
        return commentaireEvenementService.getCommentaireById(id);
    }

    // Récupérer les commentaires d'un événement spécifique
    @GetMapping("/event/{eventId}")
    public List<Commentaire_evenement> getCommentairesByEventId(@PathVariable Integer eventId) {
        return commentaireEvenementService.getCommentairesByEventId(eventId);
    }

    // Récupérer les commentaires d'un utilisateur spécifique
    @GetMapping("/user/{userId}")
    public List<Commentaire_evenement> getCommentairesByUserId(@PathVariable Integer userId) {
        return commentaireEvenementService.getCommentairesByUserId(userId);
    }

    // Récupérer les commentaires d'un événement pour un utilisateur spécifique
    @GetMapping("/event/{eventId}/user/{userId}")
    public List<Commentaire_evenement> getCommentairesByEventAndUserId(
            @PathVariable Integer eventId,
            @PathVariable Integer userId) {
        return commentaireEvenementService.getCommentairesByEventAndUserId(eventId, userId);
    }
}
