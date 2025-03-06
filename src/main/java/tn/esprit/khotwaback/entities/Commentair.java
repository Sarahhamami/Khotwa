package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity // <-- Assurez-vous que cette annotation est présente
public class Commentair{
    @Getter @Setter
    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_commentaire;

    private String contenu;
    private Date dateCommentaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "message_id")
    @JsonIgnoreProperties({"commentaires"})
    private Message message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"commentaires"})
    private User auteur;

    // Getters et Setters
    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) { // Assurez-vous que cette méthode existe
        this.contenu = contenu;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public User getAuteur() {
        return auteur;
    }

    public void setAuteur(User auteur) {
        this.auteur = auteur;
    }
    // Getters et Setters
}