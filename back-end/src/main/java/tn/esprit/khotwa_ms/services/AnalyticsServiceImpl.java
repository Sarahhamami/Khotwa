package tn.esprit.khotwa_ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.khotwa_ms.entity.UserAction;
import tn.esprit.khotwa_ms.repositories.UserActivityRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AnalyticsServiceImpl implements AnalyticsService {

    private final UserActivityRepository userActivityRepository;

    @Override
    public long getTotalActions() {
        return userActivityRepository.countTotalActions();
    }

    @Override
    public long getActionsBetweenDates(LocalDateTime start, LocalDateTime end) {
        return userActivityRepository.countActionsBetweenDates(start, end);
    }

    @Override
    public Map<String, Long> getActionCountsByType() {
        List<Object[]> results = userActivityRepository.countActionsByType();
        Map<String, Long> map = new LinkedHashMap<>();
        for (Object[] obj : results) {
            map.put(obj[0].toString(), (Long) obj[1]);
        }
        return map;
    }

    @Override
    public List<Object[]> getMostActiveUsers() {
        return userActivityRepository.findMostActiveUsers();
    }

    @Override
    public List<Object[]> getLoginsPerDay() {
        return userActivityRepository.countLoginsPerDay(UserAction.LOGIN);
    }

    @Override
    public List<Object[]> getActionsByHour() {
        return userActivityRepository.countActionsByHour();
    }

    @Override
    public List<Object[]> getMonthlyActivity() {
        return userActivityRepository.countMonthlyActivity();
    }
}
