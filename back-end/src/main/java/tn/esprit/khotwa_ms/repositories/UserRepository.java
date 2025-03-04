package tn.esprit.khotwa_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwa_ms.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);
}
