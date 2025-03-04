package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Promotion;
import java.util.List;

public interface PromotionService {
    Promotion createPromotion(Promotion promotion);
    Promotion getPromotionById(int id);
    List<Promotion> getAllPromotions();
    Promotion updatePromotion(Promotion promotion);
    void deletePromotion(int id);
}
