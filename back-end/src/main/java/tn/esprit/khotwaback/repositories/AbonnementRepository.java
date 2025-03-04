package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.PLAN_abonnement;

import java.util.List;

public interface AbonnementRepository extends JpaRepository<Abonnement,Integer> {
    List<Abonnement> findByPlan(PLAN_abonnement plan);
}
