package tn.esprit.khotwa_ms.services;

import tn.esprit.khotwa_ms.entity.UserAction;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface AnalyticsService {
    long getTotalActions();
    long getActionsBetweenDates(LocalDateTime start, LocalDateTime end);
    Map<String, Long> getActionCountsByType();
    List<Object[]> getMostActiveUsers();
    List<Object[]> getLoginsPerDay();
    List<Object[]> getActionsByHour();
    List<Object[]> getMonthlyActivity();
}
