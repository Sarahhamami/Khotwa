package tn.esprit.khotwa_ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.khotwa_ms.services.UserStatisticsService;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
public class UserStatisticsController {

    private final UserStatisticsService statisticsService;

    @GetMapping("/users/total")
    public long getTotalUsers() {
        return statisticsService.getTotalUsers();
    }

    @GetMapping("/users/role/{role}")
    public long getTotalUsersByRole(@PathVariable String role) {
        return statisticsService.getTotalUsersByRole(role);
    }

    @GetMapping("/activities/total")
    public long getTotalUserActions() {
        return statisticsService.getTotalUserActions();
    }

    @GetMapping("/activities/last-month")
    public long getUserActionsLastMonth() {
        return statisticsService.getUserActionsLastMonth();
    }
}
