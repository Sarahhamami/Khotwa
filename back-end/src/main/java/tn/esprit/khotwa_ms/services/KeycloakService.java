package tn.esprit.khotwa_ms.services;

import org.json.JSONArray;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import tn.esprit.khotwa_ms.entity.Users;

import javax.ws.rs.core.Response;
import java.util.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tn.esprit.khotwa_ms.repositories.UserRepository;

import java.util.*;

@Service

public class KeycloakService {
    @Autowired
    private UserRepository userRepository;
    private final RestTemplate restTemplate = new RestTemplate();
    @Autowired
    @Lazy
    private ServiceUser serviceUser;
   // @Autowired
    //private UsersResource usersResource;
   @Value("${keycloak.realm}")
   private String realm;

    private final Keycloak keycloak = KeycloakBuilder.builder()
            .serverUrl("http://localhost:8081")
            .realm("master")
            .clientId("admin-cli")
            .username("admin")
            .password("admin")
            .build();

    public String registerUser(String username, String email, String password, String role) {
        try {
            // Create user representation
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setEmail(email);
            user.setEnabled(true);

            // Create user credentials (password)
            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);
            user.setCredentials(Collections.singletonList(credential));

            // Set roles for the client "khotwa-rest-api"
            String clientId = "khotwa-rest-api";  // The client ID in Keycloak (replace with your actual client ID)
            Map<String, List<String>> clientRoles = new HashMap<>();
            clientRoles.put(clientId, Collections.singletonList(role));
            user.setClientRoles(clientRoles);

            // Create the user in Keycloak
            Response response = keycloak.realm("Khotwa").users().create(user);

            // Check if the response status is 201 (Created) and return the appropriate message
            if (response.getStatus() == 201) {
                // Optionally, you can retrieve the user ID from the response and assign roles if needed
                String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

                // Optionally, if the user is created successfully, assign roles
                assignRoleToUser(userId, "client_" + role);

                return "User registered successfully!";
            } else {
                return "Error creating user.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error during user registration.";
        }
    }

    public void assignRoleToUser(String userId, String roleName) {
        try {
            // Retrieve the role representation from Keycloak
            RoleRepresentation roleRepresentation = keycloak.realm("Khotwa")
                    .clients().get("khotwa-rest-api") // Replace with your actual client ID
                    .roles().get(roleName).toRepresentation();

            // Assign the role to the user
            keycloak.realm("Khotwa").users().get(userId)
                    .roles().clientLevel("khotwa-rest-api") // Replace with your client ID
                    .add(Collections.singletonList(roleRepresentation));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getUserIdByEmail(String email) {
        try {
            List<UserRepresentation> users = keycloak.realm("khotwa-realm").users().search(email);
            if (users != null && !users.isEmpty()) {
                return users.get(0).getId(); // Assuming email is unique, return the user ID
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String updateUserInKeycloak(String email, String nom, String prenom, String password, String role) {
        try {
            // Fetch user from Keycloak by email (or user ID)
            List<UserRepresentation> users = keycloak.realm("Khotwa").users().search(email);
            if (users.isEmpty()) {
                return "User not found in Keycloak";
            }

            // Get the user ID (assuming the first user in the list is the correct one)
            String userId = users.get(0).getId();

            // Update user details
            UserRepresentation user = keycloak.realm("Khotwa").users().get(userId).toRepresentation();
            user.setUsername(email);
            user.setFirstName(nom);
            user.setLastName(prenom);
            if (password != null && !password.isEmpty()) {
                // Update password (if it's provided)
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setValue(password);
                user.setCredentials(Collections.singletonList(credential));
            }

            // Update user roles
            String clientId = "khotwa-rest-api";  // The client ID in Keycloak
            Map<String, List<String>> clientRoles = new HashMap<>();
            clientRoles.put(clientId, Collections.singletonList(role));
            user.setClientRoles(clientRoles);

            // Update the user in Keycloak
            keycloak.realm("Khotwa").users().get(userId).update(user);

            return "User updated successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while updating user in Keycloak";
        }
    }

    public String getAdminAccessToken() {
        String url = "http://localhost:8081/realms/master/protocol/openid-connect/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("client_id", "admin-cli");
        body.add("username", "admin"); // Replace with your admin username
        body.add("password", "admin"); // Replace with your admin password
        body.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

        ResponseEntity<Map> response = restTemplate.postForEntity(url, request, Map.class);
        return (String) response.getBody().get("access_token");
    }

    private String getUserIdByUsername(String username, String token) {
        String url = "http://localhost:8081" + "/admin/realms/Khotwa"  + "/users?username=" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        JSONArray users = new JSONArray(response.getBody());

        return users.isEmpty() ? null : users.getJSONObject(0).getString("id");
    }

    @Transactional
    public void updateUserPassword(String username, String newPassword) {
        // Step 1: Get admin token
        String token = getAdminAccessToken();

        // Step 2: Get the user ID from Keycloak
        String userId = getUserIdByUsername(username, token);
        if (userId == null) {
            throw new RuntimeException("User not found: " + username);
        }

        // Step 3: Update password in Keycloak
        String url = "http://localhost:8081" + "/admin/realms/Khotwa" + "/users/" + userId + "/reset-password";
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> passwordUpdate = new HashMap<>();
        passwordUpdate.put("type", "password");
        passwordUpdate.put("value", newPassword);
        passwordUpdate.put("temporary", false);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(passwordUpdate, headers);
        restTemplate.put(url, entity);

        // Step 4: Update password in local database
        Optional<Users> userOptional = userRepository.findByEmail(username);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            user.setMdp(newPassword);  // Assuming you have a password field in your User model
            userRepository.save(user); // Save the updated password to DB
        } else {
            throw new RuntimeException("User not found in database: " + username);
        }

        // Step 5: Send the password reset email
        serviceUser.sendEmail(username, "Password Reset", "Your new password is: " + newPassword);
    }

    private UsersResource getUsersResource() {
        RealmResource realm1 = keycloak.realm(realm);
        return realm1.users();
    }

    public UserRepresentation getUserById(String userId) {


        return  getUsersResource().get(userId).toRepresentation();
    }

    public void deleteUserById(String userId) {

        getUsersResource().delete(userId);
    }
    public UserResource getUserResource(String userId){
        UsersResource usersResource = getUsersResource();
        return usersResource.get(userId);
    }

    public void updatePassword(String userId) {

        UserResource userResource = getUserResource(userId);
        List<String> actions= new ArrayList<>();
        actions.add("UPDATE_PASSWORD");
        userResource.executeActionsEmail(actions);

    }
    public String getUserIdByUsername(String username) {
        // Ensure Keycloak instance is valid
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl("http://localhost:8081")
                .realm(realm)
                .clientId("khotwa-rest-api")
                .username("admin") // Admin username
                .password("admin") // Admin password
                .grantType("password") // Use password grant type
                .build();

        // Access the UsersResource for the specified realm
        UsersResource usersResource = keycloak.realm(realm).users();

        // Search for the user by username
        List<UserRepresentation> users = usersResource.search(username, true);

        // Check if the user was found
        if (users.isEmpty()) {
            throw new RuntimeException("User not found in Keycloak");
        }

        // Return the ID of the first matching user
        return users.get(0).getId();
    }
    public boolean updatePassword(String newPassword, String userId) {
        try {
            // Get the UserResource for the specified user ID
            UserResource userResource = keycloak.realm(realm).users().get(userId);

            // Create a CredentialRepresentation object for the new password
            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
            credentialRepresentation.setValue(newPassword);
            credentialRepresentation.setTemporary(false); // Set the password as permanent

            // Update the password in Keycloak
            userResource.resetPassword(credentialRepresentation);

            // Return true if the password update was successful
            return true;
        } catch (Exception e) {
            // Log the error and return false if the password update failed
            System.err.println("Failed to update password in Keycloak: " + e.getMessage());
            return false;
        }
    }
}  