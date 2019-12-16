package servlets;

import cookies.CookiesService;
import dao.DaoLikedSql;
import dao.DaoUserSql;
import models.Like;
import models.User;
import services.LikeServise;
import utils.*;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Statement;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class UsersServlet extends HttpServlet {
    DaoUserSql userDAO = new DaoUserSql();
    GetLoginByCookie getLoginByCookie = new GetLoginByCookie();
    FreeMarkerByShams htmlFreeMarker = new FreeMarkerByShams();
    List<User> userList = userDAO.getAll();
    int pos=0;
    int stop=0;
    private Connection connection;
    private CookiesService cookiesService;
    private LikeServise likeServise;
    FreeMarker freeMarker = new FreeMarker();

    public UsersServlet(Connection connection) {
        this.connection=connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     cookiesService = new CookiesService(req,resp);
     int userId = Integer.parseInt(cookiesService.getCookies().getValue());
        likeServise = new LikeServise(new DaoUserSql(connection), new DaoLikedSql(userId,connection));

        User user = likeServise.otherUsers(userId);

        HashMap<String, Object> data = new HashMap<>();

        data.put("user", user);

        System.out.println("userrrrrrrrrrrrrr" + user);
        freeMarker.render("like-page.ftl", data, resp);




       /* Cookie[] cookies = req.getCookies();
        User user = userList.get(pos);
        stop = userList.size();
        TemplateEngine engine = new TemplateEngine("./content");//?
        HashMap<String, Object> data = new HashMap<>();
        data.put("id", user.getUserId());
        data.put("username", user.getNickName());
        data.put("imgURL", user.getPhotoUrl());
        engine.render("user.html", data, resp);
        //htmlFreeMarker.getHtmlPage(profile, resp, "like-page.html");
*/
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(likeServise == null){
            cookiesService = new CookiesService(req, resp);
            int activeUserId = Integer.parseInt(cookiesService.getCookies().getValue());
            likeServise = new LikeServise(new DaoUserSql(connection), new DaoLikedSql(activeUserId,connection));
        }

        FromRequest fromRequest = new FromRequest(req);
        Like like = new Like(fromRequest.getParamInt("user_id"));
        if (req.getParameter("like") != null) {
            likeServise.addLike(like);
            likeServise.addToLikeTable(fromRequest.getParamInt("user_id"));
        } else if (req.getParameter("dislike") != null) {
            likeServise.deleteLike(like);
            likeServise.addToLikeTable(fromRequest.getParamInt("user_id"));
        }
        /*while (pos < stop) {
            Map<String, Object> profile = new HashMap<>();
            User user;
            user = userList.get(pos);

            profile.put("user", user);
            /*profile.put("photo", user.getPhotoUrl());
            profile.put("login", user.getNickName());*/
           // pos++;
            //htmlFreeMarker.getHtmlPage(profile, resp, "like-page.ftl");
       // }/*
        doGet(req, resp);
    }











         /*resp.sendRedirect("./liked.html");
        //resp.sendRedirect("http://localhost:8082/liked");
        Cookie[] cookies = req.getCookies();

        if (req.getParameter("choice").equals("YES")) {
            Like liked = new Like();
            DaoLikedSql likedDAO = new DaoLikedSql();
            //here is something I cant deal with
            //if (!likedDAO.getAll().contains(req.getParameter("login")))
            liked.setUserId(Integer.parseInt(getLoginByCookie.getLogin(cookies)));
            liked.setLikedUserId(Integer.parseInt(req.getParameter("login")));
            likedDAO.save(liked);
        }

        while (pos < stop) {
            Map<String, Object> profile = new HashMap<>();
            User user;
            user = userList.get(pos);

            profile.put("name", user.getUserName());
            profile.put("photo", user.getPhotoUrl());
            profile.put("login", user.getNickName());
            pos++;

            htmlFreeMarker.getHtmlPage(profile, resp, "user.html");

        }
        resp.sendRedirect("/liked.html");
        doGet(req,resp);*/
    }



