package Servlets;

import Servlets.HelloServlet;
import Servlets.LoginServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebServer {
    public static void main(String[] args) throws Exception {
        Server server =new Server(8082);
        ServletContextHandler handler=new ServletContextHandler();
        handler.addServlet(HelloServlet.class,"/users");
        handler.addServlet(LoginServlet.class, "/*");

        server.setHandler(handler);
        server.start();
        server.join();



    }
}
