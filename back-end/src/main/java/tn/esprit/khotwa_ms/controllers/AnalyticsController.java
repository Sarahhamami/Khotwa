package tn.esprit.khotwa_ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwa_ms.services.AnalyticsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@RequiredArgsConstructor

public class AnalyticsController {

    private final AnalyticsService analyticsService;

    @GetMapping("/total-actions")
    public long getTotalActions() {
        return analyticsService.getTotalActions();
    }

    @GetMapping("/actions-between")
    public long getActionsBetweenDates(@RequestParam("start") String start,
                                       @RequestParam("end") String end) {
        return analyticsService.getActionsBetweenDates(
                LocalDateTime.parse(start),
                LocalDateTime.parse(end)
        );
    }

    @GetMapping("/action-counts")
    public Map<String, Long> getActionCountsByType() {
        return analyticsService.getActionCountsByType();
    }

    @GetMapping("/most-active-users")
    public List<Object[]> getMostActiveUsers() {
        return analyticsService.getMostActiveUsers();
    }

    @GetMapping("/logins-per-day")
    public List<Object[]> getLoginsPerDay() {
        return analyticsService.getLoginsPerDay();
    }

    @GetMapping("/actions-by-hour")
    public List<Object[]> getActionsByHour() {
        return analyticsService.getActionsByHour();
    }

    @GetMapping("/monthly-activity")
    public List<Object[]> getMonthlyActivity() {
        return analyticsService.getMonthlyActivity();
    }
}
