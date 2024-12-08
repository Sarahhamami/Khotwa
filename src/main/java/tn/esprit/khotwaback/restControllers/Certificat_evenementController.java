package tn.esprit.khotwaback.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Certificat_evenement;
import tn.esprit.khotwaback.services.Certificat_evenementService;

import java.util.List;

@RestController
@RequestMapping("/certificats")
public class Certificat_evenementController {

    @Autowired
    private Certificat_evenementService certificatEvenementService;

    @PostMapping("/generate")
    public Certificat_evenement generateCertificat(@RequestBody Certificat_evenement certificat) {
        return certificatEvenementService.generateCertificat(certificat);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCertificat(@PathVariable int id) {
        certificatEvenementService.deleteCertificat(id);
    }

    @GetMapping("/all")
    public List<Certificat_evenement> getAllCertificats() {
        return certificatEvenementService.getAllCertificats();
    }

    @GetMapping("/{id}")
    public Certificat_evenement getCertificatById(@PathVariable int id) {
        return certificatEvenementService.getCertificatById(id);
    }
}
