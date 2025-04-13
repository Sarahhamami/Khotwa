package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Abonnement;
import tn.esprit.khotwaback.entities.PLAN_abonnement;
import tn.esprit.khotwaback.repositories.AbonnementRepository;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<Abonnement> getAbonnementsSortedByPrice(String sortDirection, PLAN_abonnement plan) {
        List<Abonnement> abonnements;

        if (plan != null) {
            abonnements = abonnementRepository.findByPlan(plan);
        } else {
            abonnements = abonnementRepository.findAll();
        }

        // Appliquer le tri
        if ("asc".equalsIgnoreCase(sortDirection)) {
            abonnements.sort(Comparator.comparing(Abonnement::getPrix));
        } else if ("desc".equalsIgnoreCase(sortDirection)) {
            abonnements.sort(Comparator.comparing(Abonnement::getPrix).reversed());
        }

        return abonnements;
    }

    @Override
    public Map<String, Object> getAbonnementStatistics() {
        List<Abonnement> abonnements = abonnementRepository.findAll();
        Map<String, Object> statistics = new HashMap<>();

        // Calcul des statistiques par plan
        Map<PLAN_abonnement, Long> counts = abonnements.stream()
                .collect(Collectors.groupingBy(Abonnement::getPlan, Collectors.counting()));

        Map<PLAN_abonnement, Double> averagePrices = abonnements.stream()
                .collect(Collectors.groupingBy(Abonnement::getPlan, Collectors.averagingDouble(Abonnement::getPrix)));

        statistics.put("counts", counts);
        statistics.put("averagePrices", averagePrices);

        return statistics;
    }



}
