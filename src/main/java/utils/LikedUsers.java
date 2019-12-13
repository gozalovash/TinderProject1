package utils;

import dao.DaoUserSql;
import models.Like;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class LikedUsers {
    List<Like> likes;

    public LikedUsers(List<Like> likes) {
        this.likes = likes;
    }
    public List<User> getUserList(){
        DaoUserSql daoUserSql=new DaoUserSql();
        List<User> userList=new ArrayList<>();
        for(Like like:likes){
            User user= daoUserSql.get(like.getUserId());
            userList.add(user);
        }
        return userList;
    }
}
