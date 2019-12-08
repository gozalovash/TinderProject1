import dbconnection.DbConnection;
import filters.LoginFilter;
import org.apache.log4j.BasicConfigurator;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.ChatServlet;
import servlets.LikedServlet;
import servlets.LoginServlet;
import servlets.UsersServlet;
import utils.TemplateEngine;


import javax.servlet.DispatcherType;
import java.sql.Connection;
import java.util.EnumSet;


public class WebServer {
    public static void main(String[] args) throws Exception {
        //Connection connection = new DbConnection().connection();
        BasicConfigurator.configure();
        Server server = new Server(8082);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(LoginServlet.class, "/login/*");
        handler.addFilter(LoginFilter.class, "/login/*", EnumSet.of(DispatcherType.REQUEST));
        TemplateEngine te = new TemplateEngine("./content/");
        handler.addServlet(UsersServlet.class, "/users/*");
        handler.addServlet(new ServletHolder(new LikedServlet()), "/liked/*");
        handler.addServlet(new ServletHolder(new ChatServlet()), "/chat/*");

        //handler.addFilter(new FilterHolder(new LoginFilter(connection)),"/login/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));

        //handler.addServlet(new ServletHolder(new UsersServlet(connection)), "/users/*");
        //handler.addServlet(new ServletHolder(new LoginServlet()), "/login/*");
        // handler.addServlet(new ServletHolder(new LikedServlet(connection)), "/liked/*");
        // handler.addServlet(new ServletHolder(new MessageServlet(connection)), "/chat/*");

        //   HandlerCollection handlerCollection = new HandlerCollection();
        /// handler.addFilter(new FilterHolder(new RegistrationFilter(connection)),"/reg/*", EnumSet.of(DispatcherType.INCLUDE,DispatcherType.REQUEST));
        // handlerCollection.setHandlers(new Handler[] { handler});
        //  server.setHandler(handlerCollection);

        server.setHandler(handler);
        server.start();
        server.join();

    }
}
