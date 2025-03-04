package tn.esprit.khotwaback.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Promotion;
import tn.esprit.khotwaback.services.PromotionService;

import java.util.List;

@RestController
@RequestMapping("/promotions")
@AllArgsConstructor
public class PromotionRestController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/add")
    public Promotion addPromotion(@RequestBody Promotion promotion) {
        return promotionService.createPromotion(promotion);
    }

    @PutMapping("/update")
    public Promotion updatePromotion(@RequestBody Promotion promotion) {
        return promotionService.updatePromotion(promotion);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePromotion(@PathVariable int id) {
        promotionService.deletePromotion(id);
    }

    @GetMapping("/{id}")
    public Promotion getPromotionById(@PathVariable int id) {
        return promotionService.getPromotionById(id);
    }

    @GetMapping("/all")
    public List<Promotion> getAllPromotion() {
        return promotionService.getAllPromotions();
    }
}
