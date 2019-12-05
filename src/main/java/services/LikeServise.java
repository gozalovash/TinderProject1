package services;

import dao.DaoLikedSql;
import dao.DaoUserSql;
import freemarker.ext.servlet.FreemarkerServlet;
import utils.FreeMaker;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

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
        this.daoUserSql= new DaoUserSql(connection);
        this.likedSql= new DaoLikedSql(userId,connection);
    }

}
