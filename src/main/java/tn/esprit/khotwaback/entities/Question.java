package tn.esprit.khotwaback.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Entity
@ToString
@Setter
@Getter
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_question;
    private String ennonce;
    private String bonneReponse;
    @ManyToOne
    private Quizz quizz;
    @OneToMany(mappedBy = "question")
    private List<Reponse> reponses;
}
