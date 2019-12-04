package dao;

import com.sun.tools.javac.util.List;
import models.User;

import java.sql.Connection;

public class DaoUserSql implements Dao<User> {
    private Connection connection;


    public DaoUserSql(Connection connection) {
        this.connection = connection;
    }


    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void save(User item) {

    }

    @Override
    public void delete(User item) {

    }

    @Override
    public void update(User item) {

    }
}
