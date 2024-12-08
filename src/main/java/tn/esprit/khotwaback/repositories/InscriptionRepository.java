package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.khotwaback.entities.Evenement;
import tn.esprit.khotwaback.entities.Inscription;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {
    int countByEvenementEventId(int eventId);
    List<Inscription> findByUserId(int userId);
    List<Inscription> findByEvenementEventId(int eventId);
    int countByEvenement(Evenement evenement);
}