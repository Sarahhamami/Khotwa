package tn.esprit.khotwa_ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.khotwa_ms.entity.ROLE;
import tn.esprit.khotwa_ms.repositories.UserActivityRepository;
import tn.esprit.khotwa_ms.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class UserStatisticsService {

    private final UserRepository usersRepository;
    private final UserActivityRepository userActivityRepository;
    public long getTotalUsers() {
        return usersRepository.countTotalUsers();
    }

    public long getTotalUsersByRole(String role) {
        try {
            ROLE roleEnum = ROLE.valueOf(role.toUpperCase()); // Convert string to enum
            return usersRepository.countUsersByRole(roleEnum);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + role);
        }
    }

    public long getTotalUserActions() {
        return userActivityRepository.countTotalActions();
    }

    public long getUserActionsLastMonth() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime lastMonth = now.minus(1, ChronoUnit.MONTHS);
        return userActivityRepository.countActionsBetweenDates(lastMonth, now);
    }
}
