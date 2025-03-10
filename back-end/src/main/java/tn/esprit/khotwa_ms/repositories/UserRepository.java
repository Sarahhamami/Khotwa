package tn.esprit.khotwa_ms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.khotwa_ms.entity.Users;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String email);

    @Query("SELECT COUNT(u) FROM Users u")
    long countTotalUsers();

    @Query("SELECT COUNT(u) FROM Users u WHERE u.role = ?1")
    long countUsersByRole(String role);
}
