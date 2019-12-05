package dao;

import models.User;

import java.sql.Connection;
import java.util.List;

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
    public void delete(int id) {

    }

    /*@Override
    public void delete(User item) {

    }*/
    // for Lala: either make it delete with int here or change at Dao part to "User item" to avoid errors

    @Override
    public void update(User item) {

    }
}
