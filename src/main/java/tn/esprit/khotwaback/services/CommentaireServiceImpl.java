package tn.esprit.khotwaback.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.khotwaback.entities.Commentair;
import tn.esprit.khotwaback.entities.Message;
import tn.esprit.khotwaback.entities.User;
import tn.esprit.khotwaback.repository.CommentairRepository;
import tn.esprit.khotwaback.repository.MessageRepository;
import tn.esprit.khotwaback.repository.UserRepository;

import java.util.Date;
import java.util.List;
@Service
public class CommentaireServiceImpl {

   /* @Autowired
    private CommentairRepository commentaireRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    public Commentair addCommentaire(int messageId, int userId, String contenu) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found"));
        User auteur = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Commentair commentaire = new Commentair();
        commentaire.setContenu(contenu);
        commentaire.setDateCommentaire(new Date());
        commentaire.setAuteur(auteur);
        commentaire.setMessage(message);

        return commentaireRepository.save(commentaire);
    }

    public List<Commentair> getCommentairesByMessage(int messageId) {
        return commentaireRepository.findByMessage_Id_Message(messageId);
    }*/

}
