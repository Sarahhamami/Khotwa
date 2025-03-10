package tn.esprit.khotwa_ms.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.khotwa_ms.configuration.KeycloakAdminClientConfig;
import tn.esprit.khotwa_ms.entity.Users;
import tn.esprit.khotwa_ms.repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor

public class ServiceUser implements IServiceUser{
    private final UserRepository userRepository;

    private final JavaMailSender mailSender;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Lazy
    private final KeycloakService keycloakService;
    @Override
    public Users signup(Users user, MultipartFile image) throws IOException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new RuntimeException("L'email existe déjà.");
        }

        if (image != null && !image.isEmpty()) {
            String imageUrl = uploadImageToCloud(image);
            user.setImage(imageUrl);
        }

        return userRepository.save(user);

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

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);
    }




    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
    public Users getUserByEmail(String Email) {
        return userRepository.findByEmail(Email).orElse(null);
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }


    public Optional<Users> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public boolean sendEmail(String to, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
           // message.setFrom("sarahhammami15@gmail.com");  // Sender email address

            mailSender.send(message);  // mailSender is the JavaMailSender bean injected into your service
            return true;
        } catch (Exception e) {
            // Log the full stack trace for debugging
            e.printStackTrace();  // This will print the full stack trace
            return false;
        }
    }

    public boolean resetPassword(String email) {
        // Find the user in your database
        Optional<Users> userOpt = userRepository.findByEmail(email);

        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            String newPassword = generateRandomPassword(); // Generate a new random password

            // Encrypt the new password and update it in the database
            user.setMdp(passwordEncoder.encode(newPassword));
            userRepository.save(user);

            // Fetch the Keycloak user ID by email (username)
            String keycloakUserId = keycloakService.getUserIdByUsername(email);
            if (keycloakUserId != null) {
                // Update the password in Keycloak
                boolean keycloakUpdated = keycloakService.updatePassword(keycloakUserId, newPassword);

                if (keycloakUpdated) {
                    // Send the new password to the user via email
                    this.sendEmail(email, "Password Reset", "Your new password is: " + newPassword);
                    return true; // Password reset successful
                }
            }
        }

        // If the user is not found or Keycloak update fails, return false
        return false;
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
