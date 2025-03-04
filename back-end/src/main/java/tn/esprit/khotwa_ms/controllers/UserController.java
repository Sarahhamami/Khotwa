package tn.esprit.khotwa_ms.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.khotwa_ms.entity.ROLE;
import tn.esprit.khotwa_ms.entity.Users;
import tn.esprit.khotwa_ms.repositories.UserRepository;
import tn.esprit.khotwa_ms.services.KeycloakService;
import tn.esprit.khotwa_ms.services.ServiceUser;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final ServiceUser serviceUser;
    private final UserRepository userRepository;

    private final KeycloakService keycloakService;
    private final PasswordEncoder passwordEncoder;
    @PostMapping(value = "/addUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Users> addUser(@RequestParam("user") String userJson,
                                         @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            // Register the module for Java 8 date/time support
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

            // Deserialize the JSON string into a Users object
            Users user = mapper.readValue(userJson, Users.class);

            // If an image is provided, upload it and set the image URL on the user object
            if (image != null && !image.isEmpty()) {
                String imageUrl = uploadImageToCloud(image);
                user.setImage(imageUrl);
            }

            // Save the user via your service layer
            Users savedUser = serviceUser.addUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
   /* @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestParam String email, @RequestParam String nom, @RequestParam String prenom) {
        try {
            // Fetch the user from your local database
            Optional<Users> existingUser = userRepository.findByEmail(email);
            if (existingUser.isPresent()) {
                Users user = existingUser.get();
                user.setNom(nom);
                user.setPrenom(prenom);

                // Save the user in your local database
                userRepository.save(user);

                // Update the user in Keycloak
                Response keycloakResponse = keycloakService.updateUserInKeycloak(email, user);

                if (keycloakResponse.getStatus() == Response.Status.OK.getStatusCode()) {
                    return ResponseEntity.ok("User updated successfully!");
                } else {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating user in Keycloak: " + keycloakResponse.getEntity());
                }
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
*/





    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers() {
        return serviceUser.getAllUsers();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getUserByEmail/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email) {
        System.out.println("Fetching user by email: " + email);

        // Find user by email, the result will be an Optional
        Optional<Users> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        } else {
            System.out.println("User not found!");
            return ResponseEntity.notFound().build(); // Return 404 if not found
        }
    }


    @GetMapping("/getUserById/{id}")
    public Users getUserById(@PathVariable Integer id) {
        return serviceUser.getUserById(id);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteByIdB/{id}")
    public void deleteById(@PathVariable Integer id) {
        serviceUser.deleteById(id);
    }

    private Cloudinary getCloudinaryInstance() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dmrvc2tzn",
                "api_key", "484284376131448",
                "api_secret", "BNP07ksyINmQyqSMHBnmq2-wnzo"
        ));
    }
    private String uploadImageToCloud(MultipartFile image) throws IOException {
        Cloudinary cloudinary = getCloudinaryInstance();
        Map<String, Object> uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
        return uploadResult.get("url").toString();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    //keycloack+database adding user
    @PostMapping(value = "/registerUser", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Users> registerUser(@RequestParam String nom,
                                              @RequestParam String prenom,
                                              @RequestParam String email,
                                              @RequestParam String mdp,
                                              @RequestParam String role,
                                              @RequestParam(value = "image", required = false) MultipartFile image) {
        try {
            // Create a new user object and populate it
            Users user = new Users();
            user.setNom(nom);
            user.setPrenom(prenom);
            user.setEmail(email);
            user.setMdp(passwordEncoder.encode(mdp)); // Hash the password before saving

            // Set the role (ensure the role string corresponds to your enum)
            user.setRole(ROLE.valueOf(role.toUpperCase()));

            // If an image is provided, upload it and set the image URL on the user object
            if (image != null && !image.isEmpty()) {
                String imageUrl = uploadImageToCloud(image);
                user.setImage(imageUrl);
            }

            // 1. Register user in Keycloak
            String keycloakResponse = keycloakService.registerUser(email, email, mdp, role);
            if (!keycloakResponse.equals("User registered successfully!")) {
                throw new RuntimeException("Error while creating user in Keycloak");
            }

            // 2. Save user in your local database
            Users savedUser = serviceUser.addUser(user);

            // 3. Return the response
            return ResponseEntity.ok(savedUser);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
