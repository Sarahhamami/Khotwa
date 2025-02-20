package tn.esprit.khotwaback.restController;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.DTO.MessageDTO;
import tn.esprit.khotwaback.entities.Message;
import tn.esprit.khotwaback.services.MessageServiceImp;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageServiceImp messageService;

    @GetMapping("/getMessagesAndUserSent")
    public List<MessageDTO> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable int id) {
        return messageService.getMessageById(id);
    }
    @PostMapping("/messages")
    public ResponseEntity<MessageDTO> addMessage(@RequestBody MessageDTO messageDTO) {
        Message savedMessage = messageService.saveMessage(messageDTO);
        return ResponseEntity.ok(new MessageDTO(savedMessage));
    }
    @PutMapping
    public ResponseEntity<MessageDTO> updateMessage(@RequestBody MessageDTO messageDTO) {
        if (messageDTO.getId_message() == 0) { // Vérifier que l'ID est fourni
            return ResponseEntity.badRequest().build();
        }

        Message updatedMessage = messageService.updateMessage(messageDTO.getId_message(), messageDTO);
        return ResponseEntity.ok(new MessageDTO(updatedMessage));
    }


    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable int id) {
        messageService.deleteMessage(id);
    }

    // Récupérer les messages envoyés par un utilisateur
    @GetMapping("/expediteur/{id_user}")
    public List<Message> getMessagesByExpediteur(@PathVariable int id_user) {
        return messageService.getMessagesByExpediteur(id_user);
    }

    // Récupérer les messages reçus par un utilisateur
    @GetMapping("/destinataire/{id_user}")
    public List<Message> getMessagesByDestinataire(@PathVariable int id_user) {
        return messageService.getMessagesByDestinataire(id_user);
    }
   /* @PutMapping("/updateMessage")
    public Message updateMessage(@RequestBody Message message) {
        return messageService.updateMessage(message);
    }*/
}
