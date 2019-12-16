package services;

import dao.Dao;
import dao.DaoLikedSql;
import dao.DaoMessageSql;
import dao.DaoUserSql;
import models.Like;
import models.Message;
import models.User;
import utils.FreeMarker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MessageService {
    private Dao<Message> messageDao;
    private int senderID;
    private int receiverId;
    private Connection connection;
    private Dao<User> userDao;
    private Dao<Like> likeDao;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FreeMarker freemarker = new FreeMarker();


    public MessageService(Dao<Message> messageDao) {
        this.messageDao = messageDao;
    }

    public MessageService(int senderId, int receiverId, Connection connection, HttpServletRequest req, HttpServletResponse resp) {
        this.senderID = senderId;
        this.receiverId = receiverId;
        this.connection = connection;
        this.messageDao = new DaoMessageSql(connection, senderId);
        this.userDao = new DaoUserSql(connection);
        this.likeDao = new DaoLikedSql(senderId, connection);
        this.request = request;
        this.response = response;

    }

    public void generateLikedPage() {
        Map<String, Object> input = new HashMap<>();
        input.put("messages", 1);
        input.put("receiverId", userDao.get(receiverId));
        input.put("messageList",getMessages());
       input.put("users", getLikedUsersList(likeDao.getAll()));
        freemarker.render("people-list.ftl", input, response);
    }
    public void saveMessage(int senderId, int receiverId, String content) {
        messageDao.save(new Message(senderId, receiverId, content));
    }
    private List<Message> getMessages() {
        return messageDao.getAll().stream().filter(e -> e.getSenderId() == receiverId || e.getReceiverId() == receiverId).collect(Collectors.toList());
    }
    private List<User> getLikedUsersList(List<Like> likes) {
        return likes.stream().map(e -> userDao.get(e.getLikedUserId())).collect(Collectors.toList());
    }
}
