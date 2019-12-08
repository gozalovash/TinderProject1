package servlets;

import cookies.CookiesService;
import dao.DaoUserSql;
import models.User;
import services.UserService;
import utils.FreeMarker;
import utils.FromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


    public class RegistrationServlet extends HttpServlet  {
        private CookiesService cookiesService;
        private final FreeMarker freeMarker = new FreeMarker();
        private UserService userService;
       // private final Connection connection;

        //public RegistrationServlet(Connection connection) {
            //this.connection = connection;
            //this.userService = new UserService(new DaoUserSql(connection));
      //  }

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            HashMap<String, Object> data = new HashMap<>();

            List<String> fields = new ArrayList<>();

            fields.add("Username");
            fields.add("Name");
            fields.add("Surname");
            fields.add("Password");

            data.put("fields", fields);
            data.put("message", "Please sign up");
            data.put("rout", "/reg");

            freeMarker.render("form.ftl", data, resp);

        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            FromRequest fromRequest = new FromRequest(req);

            cookiesService = new CookiesService(req,resp);
            String nickname = fromRequest.getParamString("Username");
            String name = fromRequest.getParamString("Name");
            String surname = fromRequest.getParamString("Surname");
            String login = fromRequest.getParamString("Email");
            String password = fromRequest.getParamString("Password");
            String imgUrl = fromRequest.getParamString("ImgUrl");

            User user = new User(nickname,name,surname,password,imgUrl);
            userService.save(user);

            cookiesService.saveCookies(userService.getLogin(user));

            resp.sendRedirect("/users");
        }
    }


