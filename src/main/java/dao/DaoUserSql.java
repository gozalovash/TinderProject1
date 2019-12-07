package dao;

import models.User;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoUserSql implements Dao<User> {
    private Connection connection;


    public DaoUserSql(Connection connection) {
        this.connection = connection;
    }


    @Override
    public User get(int id) {
        User user = null;
        String SQLS = "SELECT users.username , users.name , users.surname , users.imgurl WHERE id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQLS);
            statement.setInt(1, id);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                String userName = resultSet.getString("username");
                String Name = resultSet.getString("name");
                String Surname = resultSet.getString("surname");
                String ImgUrl = resultSet.getString("imgurl");
                user = new User(id, userName, Name, Surname, ImgUrl);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return user;
    }


    @Override
    public List<User> getAll() {
        return null;
    }

    public User getByNickName(User item) {
        User user = null;
        String SQLS = "SELECT FROM users WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setString(1, user.getNickName());
            stm.execute();
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickName = resultSet.getString("username");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String photoUrl = resultSet.getString("imgUrl");
                user = new User(id, nickName, name, surname, password, photoUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    public User getByUserId(User item) {
        User user = null;
        String SQLS = "SELECT FROM users WHERE id = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setInt(1, user.getUserId());
            stm.execute();
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                String nickName = resultSet.getString("username");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String photoUrl = resultSet.getString("imgUrl");
                user = new User( nickName, name, surname, password, photoUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void save(User item) {
        String SQLI = "INSERT INTO users(id,username,name,surname,password,imgurl) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(SQLI);
            statement.setInt(1, item.getUserId());
            statement.setString(2, item.getNickName());
            statement.setString(3, item.getUserName());
            statement.setString(4, item.getUserSurname());
            statement.setString(5, item.getPassword());
            statement.setString(6, item.getPhotoUrl());

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    @Override
    public void delete(int id) {

    }


    @Override
    public void update(User item) {

    }

    public User getOtherUsers(int otherUserId) {
        return  null;
    }
}
