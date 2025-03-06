package tn.esprit.khotwaback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.ChatMessage;
import tn.esprit.khotwaback.repository.ChatMessageRepository;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public void sendMessage(ChatMessage chatMessage) {
        // Enregistrer le message en base de données
        chatMessageRepository.save(chatMessage);
        System.out.println("Message enregistré : " + chatMessage.getContent());
    }

    @Override
    public List<ChatMessage> getMessagesBetweenUsers(String sender, String recipientId) {
        // Récupérer les messages entre deux utilisateurs
        return chatMessageRepository.findBySenderAndRecipientId(sender, recipientId);
    }

    @Override
    public List<ChatMessage> getAllMessages() {
        // Récupérer tous les messages
        return chatMessageRepository.findAll();
    }

    @Override
    public List<ChatMessage> getMessagesBySender(String sender) {
        // Récupérer les messages envoyés par un utilisateur
        return chatMessageRepository.findBySender(sender);
    }

    @Override
    public void deleteMessageById(Long id) {
        // Supprimer un message par son ID
        chatMessageRepository.deleteById(id);
        System.out.println("Message supprimé avec l'ID : " + id);
    }

    @Override
    public ChatMessage getMessageById(Long id) {
        return chatMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message non trouvé avec l'ID : " + id));
    }

    @Override
    public ChatMessage updateMessage(Long id, ChatMessage chatMessage) {
        ChatMessage existingMessage = chatMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message non trouvé avec l'ID : " + id));

        existingMessage.setContent(chatMessage.getContent());
        existingMessage.setSender(chatMessage.getSender());
        existingMessage.setRecipientId(chatMessage.getRecipientId());

        return chatMessageRepository.save(existingMessage);
    }

}