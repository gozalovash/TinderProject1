package servlets;

import cookies.CookiesService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class ChatServlet extends HttpServlet {
     private Connection connection;
     private CookiesService cookiesService;
     public ChatServlet(Connection connection){
         this.connection = connection;
     }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //dont know yet
    }
}
