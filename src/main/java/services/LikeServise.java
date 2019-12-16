package services;

import dao.DaoLikedSql;
import dao.DaoUserSql;
import models.Like;
import models.User;
import utils.FreeMarker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LikeServise {
    private int userId;
    private DaoUserSql daoUserSql;
    private DaoLikedSql likedSql;
    private Connection connection;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FreeMarker freemarker = new FreeMarker();

    public LikeServise(DaoUserSql daoUserSql, DaoLikedSql likedSql) {
        this.daoUserSql = daoUserSql;
        this.likedSql = likedSql;
    }

    public LikeServise(int userId, Connection connection, HttpServletResponse response, HttpServletRequest request) {
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

    public void LikePage() {
        Map<String, Object> input = new HashMap<>();
        input.put("messages", 0);
        input.put("users", getLikedUsers(likedSql.getAll()));
        freemarker.render("people-list.ftl", input, response);
    }
    public void addToLikeTable(int dislikedUserId){
        likedSql.addToLikeTable(dislikedUserId);
    }

    public List<User> getLikedUsers(List<Like> likes) {
        return likes.stream().map(l -> daoUserSql.get(l.getLikedUserId()))
                .collect(Collectors.toList());
    }

    public User otherUsers(int otherUserId) {
        User otherUsers = daoUserSql.otherUser(otherUserId);
        if(otherUsers == null){
            likedSql.deleteLikeTable();
            return otherUsers(otherUserId);
        }
        return otherUsers;


    }

}
