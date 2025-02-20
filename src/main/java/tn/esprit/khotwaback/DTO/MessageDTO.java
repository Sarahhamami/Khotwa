package tn.esprit.khotwaback.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.khotwaback.entities.Message;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private int id_message;
    private String contenu;
    private Date dateEnvoi;
    private Integer expediteurId;

    public MessageDTO(Message message) {
        this.id_message = message.getId_message();
        this.contenu = message.getContenu();
        this.dateEnvoi = message.getDateEnvoi();
        this.expediteurId = message.getExpediteur()!= null ? message.getExpediteur().getId_user() : null;
    }
}
