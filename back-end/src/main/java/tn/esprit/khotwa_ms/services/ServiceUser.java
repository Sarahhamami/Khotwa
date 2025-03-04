package tn.esprit.khotwa_ms.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
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
    public Users updateUser(Integer id, Users newUser) {
        Optional<Users> existingUserOpt = userRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            Users existingUser = existingUserOpt.get();
            existingUser.setNom(newUser.getNom());
            existingUser.setEmail(newUser.getEmail());
            existingUser.setPrenom(newUser.getPrenom());
            existingUser.setRole(newUser.getRole());
            existingUser.setMdp(newUser.getMdp());

            // Only update the image if a new one is provided
            if (newUser.getImage() != null) {
                existingUser.setImage(newUser.getImage());
            }

            return userRepository.save(existingUser);
        } else {
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }
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
