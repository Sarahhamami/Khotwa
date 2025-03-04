package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Paiement;


import tn.esprit.khotwaback.repositories.PaiementRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class PaiementServiceImpl implements  PaiementService{
    @Autowired
    private  PaiementRepository paiementRepository;
    @Override
    public Paiement addPaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public Paiement updatePaiement(Paiement paiement) {
        return paiementRepository.save(paiement);
    }

    @Override
    public void deletePaiement(int id) {
        paiementRepository.deleteById(id);
    }

    @Override
    public Paiement getPaiementById(int id) {
        return paiementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Paiement> getAllPaiements() {
        return paiementRepository.findAll();
    }
}
