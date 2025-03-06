package tn.esprit.khotwaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.Commentair;

import java.util.List;

public interface CommentairRepository extends JpaRepository<Commentair, Integer> {
   // List<Commentair> findByMessage_Id_Message(int idmessage);

    /**
     * Récupère tous les commentaires écrits par un utilisateur.
     *
     * @param userId ID de l'utilisateur.
     * @return Liste des commentaires.
     */
   // List<Commentair> findByAuteur_IdUser(int userId);
}
