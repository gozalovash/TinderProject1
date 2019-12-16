package servlets;

import cookies.CookiesService;
import services.MessageService;
import utils.FreeMarker;
import utils.FromRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class MessageServlet extends HttpServlet {
    private Connection connection;
    private CookiesService cookiesService;
    FreeMarker freeMarker = new FreeMarker();

    public MessageServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService = new CookiesService(req, resp);
        int senderId = Integer.parseInt(cookiesService.getCookies().getValue());
        FromRequest fromRequest = new FromRequest(req);
//        String rec_str_id = req.getPathInfo().replace("/", "");
        int receiverId = fromRequest.getParamInt("user");
                //Integer.parseInt(rec_str_id);
        MessageService messageService = new MessageService(senderId, receiverId, connection, req, resp);
        messageService.generateLikedPage();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cookiesService = new CookiesService(req, resp);
        int senderId = Integer.parseInt(cookiesService.getCookies().getValue());
        FromRequest fromRequest = new FromRequest(req);
        String rec_str_id = req.getPathInfo().replace("/", "");
        int receiverId = fromRequest.getParamInt("user");
                //Integer.parseInt(rec_str_id);

        MessageService messagesService = new MessageService(senderId, receiverId, connection, req, resp);
        try {
            String text = fromRequest.getParamString("content");
            messagesService.saveMessage(senderId, receiverId, text);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        finally {
            messagesService.generateLikedPage();
        }
    }
}

