package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Setter
@Getter
public class Contenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contenu;
    private String type;
    private String fichier;
    @ManyToOne
    private Cours cours;
}
