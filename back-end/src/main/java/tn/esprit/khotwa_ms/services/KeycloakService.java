package tn.esprit.khotwa_ms.services;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwa_ms.entity.Users;

import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakService {
   // @Autowired
    //private UsersResource usersResource;
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
  /*  public Response updateUserInKeycloak(String email, Users user) {
        try {
            // Get user by email
            List<UserRepresentation> users = usersResource.search(email);

            if (users.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("User not found in Keycloak.").build();
            }

            UserRepresentation keycloakUser = users.get(0); // Get the first matching user

            // Update the user details
            keycloakUser.setFirstName(user.getNom());
            keycloakUser.setLastName(user.getPrenom());
            keycloakUser.setEmail(user.getEmail());

            // Send the update request
            Response response = usersResource.get(keycloakUser.getId()).update(keycloakUser);

            if (response.getStatus() == 204) {
                return Response.status(Response.Status.OK).entity("User updated successfully!").build();
            } else {
                return Response.status(response.getStatus()).entity("Error updating user in Keycloak: " + response.getStatus()).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error updating user in Keycloak: " + e.getMessage()).build();
        }
    }*/


}  