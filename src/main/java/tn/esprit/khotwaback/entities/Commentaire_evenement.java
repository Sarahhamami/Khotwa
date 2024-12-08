package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "commentaire_evenement")
public class Commentaire_evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String texte;
    private Date datePublication;
    private int note;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "evenement_id")
    @JsonBackReference(value = "evenement-commentaire")
    private Evenement evenement;


    public void setId_commentaire_evenement(int eventId) {
    }
}