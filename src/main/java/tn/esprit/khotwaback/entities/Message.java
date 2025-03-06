package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Entity
@ToString
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_message;
    private Date DateEnvoi;
    private String contenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"messagesEnvoyes"}) // Prevents infinite loops
    private User expediteur;
    @ManyToMany(mappedBy = "messagesRecus")
    private List<User> destinataires;

    @OneToMany(mappedBy = "message", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({"message"}) // Évite les boucles infinies
    private List<Commentair> commentaires = new ArrayList<>();


}
