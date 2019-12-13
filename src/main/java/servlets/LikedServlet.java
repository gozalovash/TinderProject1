package servlets;

import dao.DaoLikedSql;
import dao.DaoUserSql;
import models.Like;
import models.User;
import utils.FreeMarkerByShams;
import utils.GetLoginByCookie;
import utils.LikedUsers;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LikedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        GetLoginByCookie getLoginByCookie = new GetLoginByCookie();
        DaoLikedSql likedDAO = new DaoLikedSql();
        DaoUserSql userDAO = new DaoUserSql();
        List<Like> likeList = likedDAO.getAll();
        LikedUsers convert = new LikedUsers(likeList);
        List<User> userList = convert.getUserList();
        FreeMarkerByShams htmlFreeMarker = new FreeMarkerByShams();

        Map<String, Object> profile = new HashMap<>();

        profile.put("items", userList);

        htmlFreeMarker.getHtmlPage(profile, resp, "user.html");

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dont know yet
    }
}
