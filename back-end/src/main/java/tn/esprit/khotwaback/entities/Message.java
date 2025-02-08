package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;
@Entity
@ToString
@Setter
@Getter
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_message;
    private Date DateEnvoi;
    private String contenu;
    @ManyToOne
    private User expediteur;
    @ManyToMany(mappedBy = "messagesRecus")
    private List<User> destinataires;
}
