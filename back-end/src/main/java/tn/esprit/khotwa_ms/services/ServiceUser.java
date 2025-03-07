package tn.esprit.khotwa_ms.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.account.UserRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.khotwa_ms.entity.Users;
import tn.esprit.khotwa_ms.repositories.UserRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ServiceUser implements IServiceUser{
    private final UserRepository userRepository;

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
}
