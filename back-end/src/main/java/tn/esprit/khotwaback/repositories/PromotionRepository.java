package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.Promotion;

public interface PromotionRepository extends JpaRepository<Promotion,Integer> {
}
