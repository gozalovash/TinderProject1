package servlets;

import cookies.CookiesService;
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

public class LoginServlet extends HttpServlet {
    //private final Auth auth;

    /*public LoginServlet(Auth auth) {
        this.auth = auth;
    }*/
    private CookiesService cookiesService;
    FreeMarker freeMarker = new FreeMarker();
     private  Connection connection;
    UserService userService = new UserService();

     public LoginServlet(Connection connection) {
    this.connection =connection;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        List<String> fields = new ArrayList<>();
        fields.add("Username");
        fields.add("Password");

        data.put("fields", fields);
        data.put("message", "Please sign in");
        data.put("rout", "/login");

        freeMarker.render("form.ftl", data, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FromRequest request = new FromRequest(req);
        cookiesService = new CookiesService(req, resp);
        String username = request.getParamString("Username");
        String userpassword = request.getParamString("Password");
        System.out.printf("%s, %s",username, userpassword);
        User user = new User(username, userpassword);
        cookiesService.saveCookies(userService.getLogin(user));
        resp.sendRedirect("/users");
    }


}
