package tn.esprit.khotwaback.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.DTO.MessageDTO;
import tn.esprit.khotwaback.entities.Message;
import tn.esprit.khotwaback.entities.User;
import tn.esprit.khotwaback.repository.MessageRepository;
import tn.esprit.khotwaback.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class MessageServiceImp  {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    public List<MessageDTO> getAllMessages() {
        return messageRepository.findAllWithExpediteur()
                .stream()
                .map(MessageDTO::new)
                .collect(Collectors.toList());
    }


    public Optional<Message> getMessageById(int id) {
        return messageRepository.findById(id);
    }

    public Message saveMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setContenu(messageDTO.getContenu());
        message.setDateEnvoi(new Date());

        // Associer l'expéditeur
        User expediteur = userRepository.findById(messageDTO.getExpediteurId())
                .orElseThrow(() -> new RuntimeException("Expéditeur non trouvé"));
        message.setExpediteur(expediteur);

        return messageRepository.save(message);
    }


    public void deleteMessage(int id) {
        messageRepository.deleteById(id);
    }
    // Récupérer les messages envoyés par un utilisateur
    public List<Message> getMessagesByExpediteur(int id_user) {
        return messageRepository.findByExpediteur_IdUser(id_user);
    }

    // Récupérer les messages reçus par un utilisateur
    public List<Message> getMessagesByDestinataire(int id_User) {
        return messageRepository.findByDestinataires_Id_User(id_User);
    }

    //public Message updateMessage( Message updatedMessage) {
        //Message existingMessage = messageRepository.findByIdMessage(idMessage);
        /*if (existingMessage == null) {
            throw new MessageNotFoundException("Message non trouvé avec l'ID : " );
        }
        existingMessage.setContenu(updatedMessage.getContenu());
        existingMessage.setDateEnvoi(updatedMessage.getDateEnvoi());
        existingMessage.setExpediteur(updatedMessage.getExpediteur());
        existingMessage.setDestinataires(updatedMessage.getDestinataires());
        return messageRepository.save(existingMessage);*/

    public Message updateMessage(int messageId, MessageDTO messageDTO) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setContenu(messageDTO.getContenu());
        message.setDateEnvoi(messageDTO.getDateEnvoi());

        // ✅ Vérifier que l'expéditeur est bien assigné
        if (messageDTO.getExpediteurId() != null) {
            User expediteur = userRepository.findById(messageDTO.getExpediteurId())
                    .orElseThrow(() -> new RuntimeException("Expediteur not found"));
            message.setExpediteur(expediteur);
        }

        return messageRepository.save(message);
    }


}
