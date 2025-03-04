package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.PLAN_abonnement;
import tn.esprit.khotwaback.repositories.AbonnementRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class AbonnementServiceImpl implements AbonnementService {

    @Autowired
    private AbonnementRepository abonnementRepository;

    @Override
    public Abonnement addAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public Abonnement updateAbonnement(Abonnement abonnement) {
        return abonnementRepository.save(abonnement);
    }

    @Override
    public void deleteAbonnement(int id) {
        abonnementRepository.deleteById(id);
    }

    @Override
    public Abonnement getAbonnementById(int id) {
        return abonnementRepository.findById(id).orElse(null);
    }

    @Override
    public List<Abonnement> getAllAbonnements() {
        return abonnementRepository.findAll();
    }

    @Override
    public List<Abonnement> getAbonnementsByPlan(PLAN_abonnement plan) {
        return abonnementRepository.findByPlan(plan);
    }

}
