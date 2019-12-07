package servlets;

import service.Auth;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginServlet extends HttpServlet {
    //private final Auth auth;

    /*public LoginServlet(Auth auth) {
        this.auth = auth;
    }*/

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./content/login.html");
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user_name = req.getParameter("user_name");
        String user_password = req.getParameter("user_passwd");
        //boolean checked = auth.check(user_name, user_password);

        System.out.println(user_name);
        System.out.println(user_password);

        //resp.sendRedirect("http://localhost:8082/users");
        try (PrintWriter w = resp.getWriter()) {
            w.println("LoginServlet.POST");
            w.printf("user:%s %s\n", user_name,user_password);
        }
    }


    //will be changed later-- redirect to users servlet

}
