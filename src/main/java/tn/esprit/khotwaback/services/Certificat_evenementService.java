package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Certificat_evenement;
import java.util.List;

public interface Certificat_evenementService {
    Certificat_evenement generateCertificat(Certificat_evenement certificat);
    void deleteCertificat(int id);
    List<Certificat_evenement> getAllCertificats();
    Certificat_evenement getCertificatById(int id);
}
