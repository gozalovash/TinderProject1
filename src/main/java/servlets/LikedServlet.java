package servlets;

import cookies.CookiesService;
import dao.DaoLikedSql;
import dao.DaoUserSql;
import models.Like;
import models.User;
import services.LikeServise;
import utils.FreeMarkerByShams;
import utils.GetLoginByCookie;
import utils.LikedUsers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikedServlet extends HttpServlet {
    private Connection connection;
    private CookiesService cookiesService;

    public LikedServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService = new CookiesService(req, resp);
        int userId = Integer.parseInt(cookiesService.getCookies().getValue());
        LikeServise likesService = new LikeServise(userId, connection, resp, req);
        likesService.LikePage();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
