package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Promotion;
import tn.esprit.khotwaback.repositories.PromotionRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private  PromotionRepository promotionRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion getPromotionById(int id) {
        return promotionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public Promotion updatePromotion( Promotion promotion) {
        return promotionRepository.save(promotion);

    }

    @Override
    public void deletePromotion(int id) {
        promotionRepository.deleteById(id);
    }
}