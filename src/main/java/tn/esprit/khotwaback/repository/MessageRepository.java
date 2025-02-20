package tn.esprit.khotwaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.khotwaback.entities.Message;

import java.util.List;

public interface MessageRepository  extends JpaRepository<Message, Integer> {
    // Rechercher les messages envoyés par un utilisateur (expéditeur)
    @Query("SELECT m FROM Message m WHERE m.expediteur.id_user = :idUser")
    List<Message> findByExpediteur_IdUser(@Param("idUser") int idUser);

    // Rechercher les messages reçus par un utilisateur (destinataire)
    @Query("SELECT m FROM Message m JOIN m.destinataires d WHERE d.id_user = :id_user")
    List<Message> findByDestinataires_Id_User(@Param("id_user") int id_user);

    @Query("SELECT m FROM Message m JOIN FETCH m.expediteur")
    List<Message> findAllWithExpediteur();

}
