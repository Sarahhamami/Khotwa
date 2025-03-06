package tn.esprit.khotwaback.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content; // Contenu du message
    private String sender;  // Nom de l'expéditeur
    private String recipientId; // ID du destinataire

    // Constructeurs
    public ChatMessage() {}

    public ChatMessage(String content, String sender, String recipientId) {
        this.content = content;
        this.sender = sender;
        this.recipientId = recipientId;
    }
}