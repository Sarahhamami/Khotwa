package tn.esprit.khotwaback.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.khotwaback.entities.Commentair;
import tn.esprit.khotwaback.services.CommentaireServiceImpl;

import java.util.List;
//@RestController
//@RequestMapping("/commentaires")
public class CommentaireController {

   /* @Autowired
    private CommentaireServiceImpl commentaireService;

    @PostMapping("/{messageId}/{userId}")
    public Commentair addCommentaire(
            @PathVariable int messageId,
            @PathVariable int userId,
            @RequestParam String contenu) {
        return commentaireService.addCommentaire(messageId, userId, contenu);
    }

    @GetMapping("/{messageId}")
    public List<Commentair> getCommentairesByMessage(@PathVariable int messageId) {
        return commentaireService.getCommentairesByMessage(messageId);
    }*/

}
