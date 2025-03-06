package tn.esprit.khotwaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.User;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
