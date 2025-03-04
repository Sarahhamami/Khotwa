package tn.esprit.khotwa_ms.controllers;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.khotwa_ms.entity.ROLE;
import tn.esprit.khotwa_ms.entity.Users;
import tn.esprit.khotwa_ms.services.KeycloakService;
import tn.esprit.khotwa_ms.services.ServiceUser;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")

public class AuthController {

    private final KeycloakService keycloakService;


    public AuthController(KeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }
    //only register in keycloack
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String email,
                               @RequestParam String password,
                               @RequestParam String role) {
        System.out.println("Registering user: " + username);
        return keycloakService.registerUser(username, email, password, role);
    }
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) throws ServletException {
        request.logout();

        String keycloakLogoutUrl = "http://localhost:8081/realms/Khotwa/protocol/openid-connect/logout?redirect_uri=http://localhost:4200/";

        return ResponseEntity.status(HttpStatus.FOUND)
                .header("Location", keycloakLogoutUrl)
                .build();
    }


}