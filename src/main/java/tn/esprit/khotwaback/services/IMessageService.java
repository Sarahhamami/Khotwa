package tn.esprit.khotwaback.services;

import tn.esprit.khotwaback.entities.Message;

public interface IMessageService {
    public Message addMessage(Message message);
    public Message deleteMessage(Message message);
    public void deleteMessageById(int id);
}
