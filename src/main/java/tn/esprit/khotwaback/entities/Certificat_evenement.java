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
@Table(name = "certificat_evenement")
public class Certificat_evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomCertificat;
    private Date dateDelivrance;
    private String contenu;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // éviter boucle user → certificat → user
    private User user;

    @ManyToOne
    @JsonBackReference(value = "evenement-certificat")
    private Evenement evenement;

}