package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Facture;
import tn.esprit.khotwaback.repositories.FactureRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FactureServiceImpl implements FactureService {
    @Autowired
    private  FactureRepository factureRepository;

    @Override
    public Facture addFacture(Facture facture) {
        return factureRepository.save(facture);
    }

    @Override
    public Facture getFactureById(int id) {
        return factureRepository.findById(id).orElse(null);
    }

    @Override
    public List<Facture> getAllFactures() {
        return factureRepository.findAll();
    }

    @Override
    public Facture updateFacture(Facture facture) {
       return factureRepository.save(facture);
    }

    @Override
    public void deleteFacture(int id) {
        factureRepository.deleteById(id);
    }
}
