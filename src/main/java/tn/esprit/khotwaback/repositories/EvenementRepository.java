package tn.esprit.khotwaback.repositories;

import tn.esprit.khotwaback.entities.Evenement;
import tn.esprit.khotwaback.entities.Status_evenement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EvenementRepository extends JpaRepository<Evenement, Integer> {
    List<Evenement> findByStatus(Status_evenement status);
}