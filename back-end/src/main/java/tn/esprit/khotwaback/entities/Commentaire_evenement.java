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
public class Commentaire_evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_commentaire_evenement;
    private String contenu;
    private Date date_comm;
    @ManyToOne
    private User user;
    @ManyToOne
    private Evenement evenement;
}
