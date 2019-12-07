package servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

public class UsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Path path = Paths.get("./content/user.html");
        ServletOutputStream os = resp.getOutputStream();
        Files.copy(path, os);
        //it should be in a loop


        //it should display all users counting from database, with FREEMARKER
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Path path = Paths.get("./content/user.html");
        //req.getParameter()
        //PrintWriter writer =resp.getWriter();
        //writer.println("UserServlet.POST");
        //writer.close();
        //resp.sendRedirect("localhost/content/user.html");
        resp.sendRedirect("http://localhost:8082/liked");

    }
}
