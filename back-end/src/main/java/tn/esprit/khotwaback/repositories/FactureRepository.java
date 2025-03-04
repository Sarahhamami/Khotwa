package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.Facture;

public interface FactureRepository extends JpaRepository<Facture,Integer> {
}
