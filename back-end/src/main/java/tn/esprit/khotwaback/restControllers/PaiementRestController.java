package tn.esprit.khotwaback.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Paiement;
import tn.esprit.khotwaback.services.PaiementService;

import java.util.List;

@RestController
@RequestMapping("/paiements")
@AllArgsConstructor
public class PaiementRestController {

    @Autowired
    private PaiementService paiementService;

    @PostMapping("/add")
    public Paiement addPaiement(@RequestBody Paiement paiement) {
        return paiementService.addPaiement(paiement);
    }

    @PutMapping("/update/{id}")
    public Paiement updatePaiement(@RequestBody Paiement paiement) {
        return paiementService.updatePaiement(paiement);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePaiement(@PathVariable int id) {
        paiementService.deletePaiement(id);
    }

    @GetMapping("/{id}")
    public Paiement getPaiementById(@PathVariable int id) {
        return paiementService.getPaiementById(id);
    }

    @GetMapping("/all")
    public List<Paiement> getAllPaiement() {
        return paiementService.getAllPaiements();
    }
}
