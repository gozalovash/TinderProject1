package dao;

import models.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoLikedSql implements Dao<Like> {
    private Connection connection;
    private int userId;

    public DaoLikedSql(int userId, Connection connection) {
        this.connection = connection;
        this.userId = userId;
    }

    public DaoLikedSql() {
    }

    @Override
    public Like get(int id) {
        Like like = null;
        String SQLS = "SELECT * FROM liked WHERE user_id = ? AND liked_uid = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setInt(1, userId);
            stm.setInt(2, id);
            stm.execute();
            ResultSet rset = stm.executeQuery();
            if (rset.next()) {
                int userId = rset.getInt("user_id");
                int liked_uid = rset.getInt("liked_uid");
                like = new Like(userId, liked_uid);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return like;
    }

    @Override
    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        String SQLS = "SELECT * FROM liked WHERE user_id=?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLS);
            stm.setInt(1,userId);
            ResultSet rset =stm.executeQuery();
           while (rset.next())
           {
                int user_id = rset.getInt("user_id");
                int liked_uid = rset.getInt("liked_uid");
                Like like = new Like(user_id,liked_uid);
                likes.add(like);
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return likes;
    }
    public void deleteLikeTable(){
        try {
            String sql = "DELETE FROM liked WHERE user_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addToLikeTable (int checkedUserId){
        try {
            String sql = "INSERT INTO liked(user_id,liked_uid) VALUES (?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, userId);
            stm.setInt(2, checkedUserId);
            stm.execute();
        } catch (SQLException e) {
           throw  new RuntimeException("error with like data");
        }
    }

    @Override
    public void save(Like like) {
        String SQLI = "INSERT  INTO liked(user_id,liked_uid) VALUES (?,?)";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLI);
            stm.setInt(1, userId);
            stm.setInt(2, like.getLikedUserId());
            stm.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        String SQLD = " DELETE * FROM liked WHERE user_id=? AND liked_uid=?";
        try {
            PreparedStatement stm = connection.prepareStatement(SQLD);
            stm.setInt(1, userId);
            stm.setInt(2, id);
            stm.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void update(Like item) {

    }

}
