package dao;

import models.Like;

import java.sql.Connection;
import java.util.List;

public class DaoLikedSql implements Dao<Like> {
    private Connection connection;
    private int userId;

    public DaoLikedSql(int userId, Connection connection) {
        this.connection = connection;
        this.userId=userId;
    }

    @Override
    public Like get(int id) {
        return null;
    }

    @Override
    public List<Like> getAll() {
        return null;
    }

    @Override
    public void save(Like item) {

    }

    @Override
    public void delete(Like item) {

    }

    @Override
    public void update(Like item) {

    }
}
