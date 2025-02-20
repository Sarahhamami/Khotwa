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
public class Certificat_cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_certificat_cours;
    private Date Date_emmision;
    @ManyToOne
    private User user;
    @ManyToOne
    private  Cours cours;

}
