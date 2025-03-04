package tn.esprit.khotwaback.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
