package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Entity
@ToString
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_user ;
    private String nom;
    private String prenom;
    private String email ;
    private String mdp ;
    private ROLE role;
    @OneToMany(mappedBy = "expediteur")
    private List<Message> messagesEnvoyes;

    @ManyToMany
    @JoinTable(
            name = "user_message",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "message_id")
    )
    private List<Message> messagesRecus;
}
