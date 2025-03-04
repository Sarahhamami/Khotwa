package tn.esprit.khotwa_ms.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.khotwa_ms.entity.UserAction;
import tn.esprit.khotwa_ms.entity.UserActivity;
import tn.esprit.khotwa_ms.repositories.UserActivityRepository;
import tn.esprit.khotwa_ms.repositories.UserRepository;
import tn.esprit.khotwa_ms.services.UserActivityService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserActivityController {


    private final UserActivityService userActivityService;


    @GetMapping("/getUserActivityByUser/{id_user}")
    public List<UserActivity> getUserActivityByUser(@PathVariable Integer id_user){
        return userActivityService.getUserActivityByUser(id_user);
    }
    @GetMapping("/message")
    public String getMessage() {
        return userActivityService.getMessageFromFlask();  // Call Flask API
    }
    @PostMapping("/predict-satisfaction/{email}")
    public String predictSatisfaction(@PathVariable String email) {


        return userActivityService.predictSatisfaction(email);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/addUserActivity/{email}")
    public void createUserActivity(@PathVariable String email, @RequestBody Map<String, String> request) {
        String actionStr = request.get("action");
        if (actionStr == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Action is required");
        }

        try {
            UserAction action = UserAction.valueOf(actionStr.toUpperCase());
            userActivityService.createUserActivity(email, action);
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid action type");
        }
    }




}
