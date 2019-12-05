package services;

import dao.DaoLikedSql;
import dao.DaoUserSql;
import freemarker.ext.servlet.FreemarkerServlet;
import models.Like;
import models.User;
import utils.FreeMaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.util.List;
import java.util.stream.Collectors;

public class LikeServise {
    private int userId;
    private DaoUserSql daoUserSql;
    private DaoLikedSql likedSql;
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletRequest response;
    private FreeMaker freemarker = new FreeMaker();

    public LikeServise(DaoUserSql daoUserSql, DaoLikedSql likedSql) {
        this.daoUserSql = daoUserSql;
        this.likedSql = likedSql;
    }

    public LikeServise(int userId, Connection connection, HttpServletRequest response, HttpServletRequest request) {
        this.userId = userId;
        this.connection = connection;
        this.response = response;
        this.request = request;
        this.daoUserSql = new DaoUserSql(connection);
        this.likedSql = new DaoLikedSql(userId, connection);
    }

    public void addLike(Like like) {
        if (likedSql.get(like.getLikedUserId()) == null) {
            likedSql.save(like);
        }
    }

    public void deleteLike(Like like) {
        if (likedSql.get(like.getLikedUserId()) != null) {
            likedSql.delete(like.getLikedUserId());
        }
    }

    public List<User> getLikedUsers(List<Like> likes) {
        return likes.stream().map(l -> daoUserSql.get(l.getLikedUserId()))
                .collect(Collectors.toList());
    }
    public User otherUsers(int otherUserId){
      User user =daoUserSql.getOtherUsers(otherUserId);
      //implement
        return user;
    }

}
