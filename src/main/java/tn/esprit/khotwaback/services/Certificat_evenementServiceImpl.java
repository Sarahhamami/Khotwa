package tn.esprit.khotwaback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Certificat_evenement;
import tn.esprit.khotwaback.repositories.Certificat_evenementRepository;
import tn.esprit.khotwaback.services.Certificat_evenementService;

import java.util.List;

@Service
public class Certificat_evenementServiceImpl implements Certificat_evenementService {

    @Autowired
    private Certificat_evenementRepository certificatEvenementRepository;

    @Override
    public Certificat_evenement generateCertificat(Certificat_evenement certificat) {
        return certificatEvenementRepository.save(certificat);
    }

    @Override
    public void deleteCertificat(int id) {
        certificatEvenementRepository.deleteById(id);
    }

    @Override
    public List<Certificat_evenement> getAllCertificats() {
        return certificatEvenementRepository.findAll();
    }

    @Override
    public Certificat_evenement getCertificatById(int id) {
        return certificatEvenementRepository.findById(id).orElse(null);
    }
}
