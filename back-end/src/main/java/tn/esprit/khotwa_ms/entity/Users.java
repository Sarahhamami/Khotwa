package tn.esprit.khotwa_ms.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_user ;
    private String nom;
    private String prenom;
    private String email ;
    private String mdp ;
    @Enumerated(EnumType.STRING)
    private ROLE role;
    private String image;
}
