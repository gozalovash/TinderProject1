package filters;

import dao.DaoUserSql;
import models.User;
import org.eclipse.jetty.http.HttpMethod;
import services.UserService;
import utils.FreeMarker;
import utils.FromRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class RegistrationFilter implements Filter {
    private UserService userService;
    FreeMarker freeMarker = new FreeMarker();
    private final Connection connection;

    public RegistrationFilter(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new DaoUserSql(connection));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {


    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest;
        if (request instanceof HttpServletRequest) {
            servletRequest = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("Something went wrong with HttpServletRequest");
        }
        HashMap<String, Object> data = new HashMap<>();
        if (HttpMethod.POST.name().equalsIgnoreCase(servletRequest.getMethod())) {

            try {
                FromRequest fromRequest = new FromRequest(servletRequest);
                String login = fromRequest.getParamString("Username");
                System.out.println("username");
                String name = fromRequest.getParamString("Name");
                System.out.println("name");
                String surname = fromRequest.getParamString("Surname");
                System.out.println("surname");
                String password = fromRequest.getParamString("Password");
                System.out.println("password");
                String url = fromRequest.getParamString("Url");
                System.out.println("url");
                User user = new User(login, name, surname, password, url);
                if (userService.getByLogin(user)) {
                    throw new IllegalArgumentException("This user already exists");
                }
                chain.doFilter(request, response);
            } catch (Exception e) {
                data.put("message", e.getMessage());
                data.put("rout", "reg");
               freeMarker.render("fail.ftl", data, (HttpServletResponse) response);
            }
        } else {
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {

    }
}
