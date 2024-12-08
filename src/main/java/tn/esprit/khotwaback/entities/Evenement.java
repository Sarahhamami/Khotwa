package tn.esprit.khotwaback.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Evenement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int eventId;
    private String title;
    private String description;
    private Date date;
    private String location;

    @Enumerated(EnumType.STRING)
    private Type_evenement type;
    private int capacite;

    private int maxParticipants;
    private int currentParticipants;
    private String imageUrl;
    @Enumerated(EnumType.STRING)
    private Status_evenement status;

    @OneToMany(mappedBy = "evenement")
    @JsonManagedReference(value = "evenement-certificat")
    private List<Certificat_evenement> certificatEvenements;

    @OneToMany(mappedBy = "evenement")
    @JsonManagedReference(value = "evenement-commentaire")
    private List<Commentaire_evenement> commentaireEvenements;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore // pour éviter la boucle user → events → user
    private User user;




}