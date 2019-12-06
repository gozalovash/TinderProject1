package services;

import dao.Dao;
import models.Message;

import java.util.List;
import java.util.stream.Collectors;

public class MessageService {
    private Dao<Message> messageDao;

    public MessageService(Dao<Message> messageDao) {
        this.messageDao = messageDao;
    }

    public List<Message> getMessages(int senderId, int receiverId) {
        return messageDao.getAll().stream().filter(s -> s.getSenderId() == senderId).
                filter(r -> r.getReceiverId() == receiverId).collect(Collectors.toList());
    }

    public void saveMessage(int senderId, int receiverId, String text) {
        messageDao.save(new Message(senderId, receiverId, text));
    }
    public List<Message> receivedMessage(int senderId){
        return messageDao.getAll().stream().filter(s->s.getSenderId()==senderId).collect(Collectors.toList());

    }
    public List<Message> sentMessage(int receiverId){
        return  messageDao.getAll().stream().filter(r->r.getReceiverId()==receiverId).collect(Collectors.toList());
    }
}
