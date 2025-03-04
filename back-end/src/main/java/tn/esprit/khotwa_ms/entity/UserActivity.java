package tn.esprit.khotwa_ms.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@ToString
@Setter
@Getter
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users user;
    private LocalDateTime action_date;
    @Enumerated(EnumType.STRING)
    private UserAction action;
}
