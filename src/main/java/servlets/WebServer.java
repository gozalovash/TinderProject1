package servlets;

import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class WebServer {
    public static void main(String[] args) throws Exception {
        BasicConfigurator.configure();
        Server server =new Server(8082);
        ServletContextHandler handler=new ServletContextHandler();
        handler.addServlet(UsersServlet.class,"/users/*");
        handler.addServlet(LoginServlet.class, "/login/*");

        server.setHandler(handler);
        server.start();
        server.join();



    }
}
