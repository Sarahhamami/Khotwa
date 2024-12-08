package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inscriptionId;

    private String nom;
    private String email;
    private String telephone;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "event_id")
    private Evenement evenement;

    private Date dateInscription = new Date();
}