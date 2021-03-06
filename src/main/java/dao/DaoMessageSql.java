package dao;

import models.Message;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoMessageSql implements Dao<Message> {
    private Connection connection;
    private int senderId,receiverId;

    public DaoMessageSql(Connection connection) {
        this.connection = connection;
    }

    public DaoMessageSql(Connection connection, int senderId,int receiverId) {
        this.connection = connection;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public Message get(int id) {
        try {
            java.lang.String sql = "SELECT * FROM message WHERE message_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Message( resultSet.getInt("sender"), resultSet.getInt("receiver"), resultSet.getString("content"));
            }
            return null;

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }


    @Override
    public List<Message> getAll() {
        List<Message> messageList = new ArrayList<>();
        String SQLS = "SELECT * FROM message WHERE (sender = ? AND  receiver = ? )OR (receiver = ? AND  sender = ? )";
        try {
            PreparedStatement statement = connection.prepareStatement(SQLS);
            System.out.println(senderId);
            statement.setInt(1, senderId);
            statement.setInt(2, receiverId);
            statement.setInt(3, senderId);
            statement.setInt(4, receiverId);
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                String history = resultSet.getInt("receiver") == senderId ? "received" : "sent";
                messageList.add(new Message(resultSet.getInt("sender"),
                        resultSet.getInt("receiver"),
                        resultSet.getString("content")));
            }
            return messageList;
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong in get all");
        }
    }

    @Override
    public void save(Message item) {
        String SQLI = "INSERT  INTO message(sender,receiver,content) VALUES(?,?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLI);
            stm.setInt(1, senderId);
            stm.setInt(2, item.getReceiverId());
            stm.setString(3, item.getText());
            stm.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Something went wrong during uploading");
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Message item) {

    }
}
