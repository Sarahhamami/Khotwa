package tn.esprit.khotwaback.restControllers;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Evenement;
import tn.esprit.khotwaback.entities.Status_evenement;
import tn.esprit.khotwaback.services.EmailService;
import tn.esprit.khotwaback.services.EvenementService;
import tn.esprit.khotwaback.services.EvenementServiceImpl;

import com.opencsv.CSVWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/evenements")
@CrossOrigin(origins = "*")
public class EvenementController {

    @Autowired
    private EvenementService evenementService;



    @Autowired
    private EmailService emailService; // make sure this is correctly defined

    @PostMapping("/create")
    public ResponseEntity<Evenement> createEvenement(@RequestBody Evenement evenement) {
        Evenement savedEvent = evenementService.createEvenement(evenement);

        // Prepare email
        String subject = "Nouvel Événement Créé";
        String message = "Un nouvel événement a été ajouté :\n\n" +
                "Titre : " + savedEvent.getTitle() + "\n" +
                "Date : " + savedEvent.getDate() + "\n" +
                "Lieu : " + savedEvent.getLocation() + "\n" +
                "Type : " + savedEvent.getType();

        String recipientEmail = "adambenabdallah712@gmail.com"; // change to your actual recipient

        emailService.sendVerificationCode(recipientEmail, message);

        return ResponseEntity.ok(savedEvent);
    }


    @PutMapping("/update/{id}")
    public Evenement updateEvenement(@PathVariable("id") int eventId, @RequestBody Evenement evenement) {
        return evenementService.updateEvenement(eventId, evenement);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEvenement(@PathVariable("id") int eventId) {
        evenementService.deleteEvenement(eventId);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllEvenements() {
        try {
            List<Evenement> evenements = evenementService.getAllEvenements();
            return ResponseEntity.ok(evenements);
        } catch (Exception e) {
            // Log de l'erreur (important pour le debug)
            e.printStackTrace();

            // Retourne une erreur 500 mais avec un message lisible
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Une erreur est survenue lors de la récupération des événements.");
        }
    }

    @GetMapping("/{eventId}")
    public Evenement getEvenementById(@PathVariable int eventId) {
        return evenementService.getEvenementById(eventId);
    }


    @GetMapping("/{id}/qrcode")
    public ResponseEntity<byte[]> generateQRCodeForEvent(
            @PathVariable int id,
            @RequestParam(defaultValue = "200") int width,
            @RequestParam(defaultValue = "200") int height) {
        try {
            byte[] qrCode = evenementService.generateQRCode(id, width, height);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(qrCode);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Error generating QR code: " + e.getMessage()).getBytes());
        }
    }

    @GetMapping("/export/csv")
    public ResponseEntity<byte[]> exportEventsToCSV() {
        List<Evenement> evenements = evenementService.getAllEvenements();

        if (evenements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        StringWriter stringWriter = new StringWriter();
        try (CSVWriter csvWriter = new CSVWriter(stringWriter)) {
            // CSV header
            String[] header = {"ID", "Title", "Description", "Date", "Location", "Type"};
            csvWriter.writeNext(header);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            for (Evenement e : evenements) {
                String[] row = {
                        String.valueOf(e.getEventId()),
                        e.getTitle(),
                        e.getDescription(),
                        e.getDate() != null ? formatter.format(e.getDate()) : "N/A",
                        e.getLocation(),
                        e.getType() != null ? e.getType().name() : "N/A"
                };
                csvWriter.writeNext(row);
            }

            byte[] csvBytes = stringWriter.toString().getBytes(StandardCharsets.UTF_8);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", "evenements.csv");
            headers.setContentLength(csvBytes.length);

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(csvBytes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/search/by-status")
    public ResponseEntity<List<Evenement>> getEvenementsByStatus(@RequestParam("status") Status_evenement status) {
        List<Evenement> evenements = evenementService.findByStatus(status);

        if (evenements.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(evenements);
    }


}