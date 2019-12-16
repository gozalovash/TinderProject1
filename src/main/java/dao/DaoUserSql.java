package dao;

import dbconnection.DbConnection;
import models.User;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoUserSql implements Dao<User> {
    private Connection connection;
    private List<User> users;
    public DaoUserSql(){
        users=new LinkedList<>();
        this.connection = DbConnection.connection();
    }



    public DaoUserSql(Connection connection) {
        this.connection = connection;
       // getByLogin();
    }


    @Override
    public User get(int id) {
        User user = null;
        String SQLS = "SELECT users.username , users.name , users.surname , users.imgurl FROM users WHERE id =?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQLS);
            statement.setInt(1, id);
           // statement.execute();
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
        User user = null;
        String SQLS = "SELECT users.id , users.username , users.name , users.surname , users.imgurl FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(SQLS);

            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            while ((resultSet.next())) {
                String userName = resultSet.getString("username");
                String Name = resultSet.getString("name");
                String Surname = resultSet.getString("surname");
                String ImgUrl = resultSet.getString("imgurl");
                int id = resultSet.getInt("id");
                user = new User(id, userName, Name, Surname, ImgUrl);
                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return users;


    }
    public User otherUser(int userId) {
        User result = null;

        String SQLS = "SELECT * FROM users WHERE id != ? AND id NOT IN (\n" +
                "    SELECT liked_uid FROM liked WHERE liked_uid = id AND user_id = ? \n" +
                ") LIMIT 1";


        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setInt(1, userId);
            stm.setInt(2,userId);
            ResultSet rSet = stm.executeQuery();

            if (rSet.next()) {

                int id = rSet.getInt("id");
                String login = rSet.getString("username");
                String name = rSet.getString("name");
                String surname = rSet.getString("surname");
                String password = rSet.getString("password");
                String imgurl = rSet.getString("imgurl");
                result = new User(id, login, name, surname,password,imgurl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }


    public User getByLogin(User item) {
        User user = null;
        String SQLS = "SELECT * FROM users WHERE username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setString(1, item.getNickName());
            ResultSet resultSet = stm.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nickName = resultSet.getString("username");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String password = resultSet.getString("password");
                String photoUrl = resultSet.getString("imgUrl");
                user = new User(id,nickName,name,surname,password,photoUrl);
                System.out.println("checking");
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("checking2");
        return user;
    }
    public User getByUserId(User item) {
        User user = null;
        String SQLS = "SELECT * FROM users WHERE id = ?";
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
        String SQLI = "INSERT INTO users(username,name,surname,password,imgurl) VALUES(?,?,?,?,?)";
        User user=null;

        try {
            PreparedStatement statement = connection.prepareStatement(SQLI);
            statement.setString(1, item.getNickName());
            statement.setString(2, item.getUserName());
            statement.setString(3, item.getUserSurname());
            statement.setString(4, item.getPassword());
            statement.setString(5, item.getPhotoUrl());
            statement.execute();
            user=new User(item.getNickName(),item.getUserName(),item.getUserSurname(),item.getPassword()
            ,item.getPhotoUrl());
            users.add(user);
            System.out.println("save postgres");

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

}
