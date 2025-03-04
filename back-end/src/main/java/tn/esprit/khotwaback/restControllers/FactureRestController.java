package tn.esprit.khotwaback.restControllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Facture;
import tn.esprit.khotwaback.services.FactureService;

import java.util.List;

@RestController
@RequestMapping("/factures")
@AllArgsConstructor
public class FactureRestController {

    @Autowired
    private FactureService factureService;

    @PostMapping("/add")
    public Facture addFacture(@RequestBody Facture facture) {
        return factureService.addFacture(facture);
    }

    @PutMapping("/update/{id}")
    public Facture updateFacture(@RequestBody Facture facture) {
        return factureService.updateFacture(facture);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteFacture(@PathVariable int id) {
        factureService.deleteFacture(id);
    }

    @GetMapping("/{id}")
    public Facture getFactureById(@PathVariable int id) {
        return factureService.getFactureById(id);
    }

    @GetMapping("/all")
    public List<Facture> getAllFactures() {
        return factureService.getAllFactures();
    }
}
