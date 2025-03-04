package tn.esprit.khotwaback.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.PLAN_abonnement;
import tn.esprit.khotwaback.services.AbonnementService;

import java.util.List;

@RestController
@RequestMapping("/abonnements")
@AllArgsConstructor
public class AbonnementRestController {

    @Autowired
    private AbonnementService abonnementService;

    @PostMapping("/add")
    public Abonnement addAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementService.addAbonnement(abonnement);
    }

    @PutMapping("/update/{id}")
    public Abonnement updateAbonnement(@RequestBody Abonnement abonnement) {
        return abonnementService.updateAbonnement(abonnement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAbonnement(@PathVariable int id) {
        abonnementService.deleteAbonnement(id);
    }

    @GetMapping("/{id}")
    public Abonnement getAbonnementById(@PathVariable int id) {
        return abonnementService.getAbonnementById(id);
    }

    @GetMapping("/all")
    public List<Abonnement> getAllAbonnements() {
        return abonnementService.getAllAbonnements();
    }
    @GetMapping("/allByPlan")
    public List<Abonnement> getAbonnementsByPlan(@RequestParam PLAN_abonnement plan) {
        return abonnementService.getAbonnementsByPlan(plan);
    }
}
