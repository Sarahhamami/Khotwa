package tn.esprit.khotwa_ms.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import tn.esprit.khotwa_ms.entity.UserAction;
import tn.esprit.khotwa_ms.entity.UserActivity;
import tn.esprit.khotwa_ms.entity.Users;
import tn.esprit.khotwa_ms.repositories.UserActivityRepository;
import tn.esprit.khotwa_ms.repositories.UserRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class UserActivityService {

    private final UserRepository userRepository;
    private final UserActivityRepository userActivityRepository;
    private final WebClient webClient;
    public List<UserActivity> getUserActivityByUser(Integer id_user){
        return userActivityRepository.findActivitiesByUserId(id_user);
    }
    public void addUserActivity(UserActivity userActivity){
        userActivityRepository.save(userActivity);
    }
    public Integer countByAction(Integer id_user, UserAction userAction) {
        return userActivityRepository.countByUserAndAction(id_user,userAction);
    }


    public Integer getDaysSinceLastActivity(Integer id_user) {
        // Fetch all activities for the given user
        List<UserActivity> activities = userActivityRepository.findByUser(id_user);

        if (!activities.isEmpty()) {
            // Calculate the number of days since the last activity
            LocalDateTime mostRecentActivity = activities.stream()
                    .map(UserActivity::getAction_date)  // Extract the date of each activity
                    .max(LocalDateTime::compareTo)      // Get the most recent one
                    .orElseThrow(() -> new RuntimeException("No activities found"));

            return (int) ChronoUnit.DAYS.between(mostRecentActivity, LocalDateTime.now());
        }

        return 0; // Return -1 if no activities found
    }


    public UserActivityService(UserRepository userRepository, UserActivityRepository userActivityRepository, WebClient.Builder webClientBuilder) {
        this.userRepository = userRepository;
        this.userActivityRepository = userActivityRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:5000").build();// flask api url

    }

    public String getMessageFromFlask() {
        return this.webClient.get()
                .uri("/api/message")
                .retrieve()
                .bodyToMono(String.class)
                .block(); // block is used to wait for the response in this case (synchronous call)
    }



    public String predictSatisfaction(String email) {
        try {
            // Fetch user and activity data
            Users user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Integer id_user = user.getId_user();
            int loginCount = countByAction(id_user, UserAction.LOGIN);
            int profileUpdated = countByAction(id_user, UserAction.PROFILE_UPDATE);
            int lastActivityDays = getDaysSinceLastActivity(id_user);

            Map<String, Object> data = new HashMap<>();
            data.put("loginCount", loginCount);
            data.put("profileUpdated", profileUpdated);
            data.put("lastLoginDays", lastActivityDays);

            // Make the POST request to Flask API for prediction
            return this.webClient.post()
                    .uri("/predict")
                    .bodyValue(data)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        } catch (Exception e) {
            // Log the error and return an error response
            log.error("Error during satisfaction prediction", e);
            return "Error processing prediction: " + e.getMessage();
        }
    }

    public void createUserActivity(String email, UserAction action) {
        Optional<Users> optionalUser = userRepository.findByEmail(email);

        // Check if the user is present
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get(); // Extract the Users object from Optional

            // Create the UserActivity object
            UserActivity userActivity = UserActivity.builder()
                    .user(user) // Pass the user object, not the Optional
                    .action_date(LocalDateTime.now())
                    .action(action)
                    .build();

            // Save the UserActivity
            userActivityRepository.save(userActivity);
        } else {
            // Handle the case where the user is not found (e.g., throw an exception or log a warning)
            throw new RuntimeException("User not found with email: " + email);
        }
    }


}
