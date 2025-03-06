package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.ChatMessage;

import java.util.List;

public interface ChatService {
    // Envoyer un message
    void sendMessage(ChatMessage chatMessage);

    // Récupérer les messages entre deux utilisateurs
    List<ChatMessage> getMessagesBetweenUsers(String sender, String recipientId);

    // Récupérer tous les messages
    List<ChatMessage> getAllMessages();

    // Récupérer les messages envoyés par un utilisateur
    List<ChatMessage> getMessagesBySender(String sender);

    // Supprimer un message par son ID
    void deleteMessageById(Long id);

    ChatMessage getMessageById(Long id);
    ChatMessage updateMessage(Long id, ChatMessage chatMessage);
}