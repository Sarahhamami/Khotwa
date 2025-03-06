package tn.esprit.khotwaback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.khotwaback.entities.ChatMessage;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    // Récupérer les messages entre deux utilisateurs
    List<ChatMessage> findBySenderAndRecipientId(String sender, String recipientId);

    // Récupérer les messages envoyés par un utilisateur
    List<ChatMessage> findBySender(String sender);
}