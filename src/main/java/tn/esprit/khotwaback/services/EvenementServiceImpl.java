package tn.esprit.khotwaback.services;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Evenement;
import tn.esprit.khotwaback.entities.Status_evenement;
import tn.esprit.khotwaback.repositories.EvenementRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EvenementServiceImpl implements EvenementService {

    @Autowired
    private EvenementRepository evenementRepository;

    @Override
    public Evenement createEvenement(Evenement evenement) {
        return evenementRepository.save(evenement);
    }

    @Override
    public Evenement updateEvenement(int eventId, Evenement evenement) {
        return evenementRepository.findById(eventId)
                .map(existingEvent -> {
                    existingEvent.setTitle(evenement.getTitle());
                    existingEvent.setDescription(evenement.getDescription());
                    existingEvent.setDate(evenement.getDate());
                    existingEvent.setLocation(evenement.getLocation());
                    existingEvent.setType(evenement.getType());
                    existingEvent.setMaxParticipants(evenement.getMaxParticipants());
                    existingEvent.setCurrentParticipants(evenement.getCurrentParticipants());
                    existingEvent.setImageUrl(evenement.getImageUrl());
                    existingEvent.setStatus(evenement.getStatus());
                    return evenementRepository.save(existingEvent);
                })
                .orElse(null);
    }

    @Override
    public void deleteEvenement(int eventId) {
        evenementRepository.findById(eventId).ifPresent(evenementRepository::delete);
    }

    @Override
    public List<Evenement> getAllEvenements() {
        return evenementRepository.findAll();
    }

    @Override
    public Evenement getEvenementById(int eventId) {
        return evenementRepository.findById(eventId).orElse(null);
    }



    @Override
    public byte[] generateQRCode(int eventId, int width, int height) throws Exception {
        Evenement evenement = evenementRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found with ID: " + eventId));

        String eventDetails = String.format(
                "Event ID: %d\nTitle: %s\nDescription: %s\nDate: %s\nLocation: %s\nType: %s\nStatus: %s",
                evenement.getEventId(),
                evenement.getTitle(),
                evenement.getDescription(),
                evenement.getDate().toString(),
                evenement.getLocation(),
                evenement.getType().toString(),
                evenement.getStatus().toString()
        );

        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(eventDetails, BarcodeFormat.QR_CODE, width, height);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        } catch (WriterException | IOException e) {
            throw new Exception("Failed to generate QR code", e);
        }
    }

    @Override
    public List<Evenement> findByStatus(Status_evenement status) {
        return evenementRepository.findByStatus(status);
    }

}