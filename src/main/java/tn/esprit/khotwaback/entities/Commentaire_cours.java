package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Entity
@ToString
@Setter
@Getter
public class Commentaire_cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_commentaire_cours;
    private String contenu;
    private Date date_comm;
    @ManyToOne
    private User user;
    @ManyToOne
    private  Cours cours;
}
