package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.khotwaback.entities.Commentaire_evenement;

import java.util.List;

public interface Commentaire_evenementRepository extends JpaRepository<Commentaire_evenement, Integer> {
    // Récupérer tous les commentaires pour un événement spécifique
    List<Commentaire_evenement> findByEvenementEventId(Integer eventId);
    
    // Récupérer tous les commentaires pour un utilisateur spécifique
    List<Commentaire_evenement> findByUserId(Integer userId);
    
    // Récupérer tous les commentaires pour un événement et un utilisateur spécifiques
    @Query("SELECT c FROM Commentaire_evenement c WHERE c.evenement.eventId = :eventId AND c.user.id = :userId")
    List<Commentaire_evenement> findByEvenementAndUser(@Param("eventId") Integer eventId, @Param("userId") Integer userId);
}
