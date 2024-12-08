package tn.esprit.khotwaback.restControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Inscription;
import tn.esprit.khotwaback.services.InscriptionService;

@RestController
@RequestMapping("/inscriptions")
@CrossOrigin(origins = "*")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping("/create/{eventId}/{userId}")
    public ResponseEntity<?> createInscription(
            @PathVariable int eventId,
            @PathVariable int userId) {

        Inscription savedInscription = inscriptionService.createInscription(userId, eventId);

        if (savedInscription != null) {
            return ResponseEntity.ok(savedInscription);
        }
        return ResponseEntity.badRequest().body("L'événement a atteint sa capacité maximale ou l'utilisateur n'existe pas");
    }

    @GetMapping("/count/{eventId}")
    public int getNombreInscriptions(@PathVariable int eventId) {
        return inscriptionService.getNombreInscriptions(eventId);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserInscriptions(@PathVariable int userId) {
        return ResponseEntity.ok(inscriptionService.getUserInscriptions(userId));
    }

    @GetMapping("/event/{eventId}")
    public ResponseEntity<?> getEventInscriptions(@PathVariable int eventId) {
        return ResponseEntity.ok(inscriptionService.getEventInscriptions(eventId));
    }

    @DeleteMapping("/delete/{inscriptionId}")
    public ResponseEntity<Void> deleteInscription(@PathVariable int inscriptionId) {
        inscriptionService.deleteInscription(inscriptionId);
        return ResponseEntity.noContent().build();
    }
}