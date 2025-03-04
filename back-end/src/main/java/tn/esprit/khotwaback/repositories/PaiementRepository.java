package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement,Integer> {
}
