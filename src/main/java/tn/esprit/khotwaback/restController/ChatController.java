package tn.esprit.khotwaback.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.ChatMessage;
import tn.esprit.khotwaback.services.ChatService;

import java.util.List;

@Controller // Pour WebSocket
@RestController // Pour REST
@RequestMapping("/api/chat") // Préfixe pour les endpoints REST
public class ChatController {

    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(ChatService chatService, SimpMessagingTemplate messagingTemplate) {
        this.chatService = chatService;
        this.messagingTemplate = messagingTemplate;
    }

    // Endpoint WebSocket pour envoyer un message privé
    @MessageMapping("/chat.sendPrivateMessage")
    public void sendPrivateMessage(ChatMessage chatMessage) {
        // Enregistrer le message en base de données
        chatService.sendMessage(chatMessage);

        // Envoyer le message au destinataire
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId(), // ID du destinataire
                "/queue/messages",            // Destination privée
                chatMessage                   // Message à envoyer
        );
    }

    // Endpoint REST pour récupérer tous les messages
    @GetMapping("/messages")
    public List<ChatMessage> getAllMessages() {
        return chatService.getAllMessages();
    }

    // Endpoint REST pour récupérer les messages entre deux utilisateurs
    @GetMapping("/messages/between/{sender}/{recipientId}")
    public List<ChatMessage> getMessagesBetweenUsers(
            @PathVariable String sender,
            @PathVariable String recipientId) {
        return chatService.getMessagesBetweenUsers(sender, recipientId);
    }

    // Endpoint REST pour récupérer les messages envoyés par un utilisateur
    @GetMapping("/messages/sender/{sender}")
    public List<ChatMessage> getMessagesBySender(@PathVariable String sender) {
        return chatService.getMessagesBySender(sender);
    }

    // Endpoint REST pour supprimer un message par son ID
    @DeleteMapping("/messages/{id}")
    public void deleteMessageById(@PathVariable Long id) {
        chatService.deleteMessageById(id);
    }

    // Endpoint REST pour créer un message
    @PostMapping("/messages")
    public ChatMessage createMessage(@RequestBody ChatMessage chatMessage) {
        chatService.sendMessage(chatMessage);
        return chatMessage;
    }

    // Endpoint REST pour récupérer un message par son ID
    @GetMapping("/messages/{id}")
    public ChatMessage getMessageById(@PathVariable Long id) {
        return chatService.getMessageById(id);
    }

    // Endpoint REST pour mettre à jour un message
    @PutMapping("/messages/{id}")
    public ChatMessage updateMessage(@PathVariable Long id, @RequestBody ChatMessage chatMessage) {
        return chatService.updateMessage(id, chatMessage);
    }
}