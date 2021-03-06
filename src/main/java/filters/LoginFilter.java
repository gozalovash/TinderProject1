package filters;

import dao.DaoUserSql;
import models.User;
import org.eclipse.jetty.http.HttpMethod;
import services.UserService;
import utils.FreeMarker;
import utils.FromRequest;
import utils.TemplateEngine;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class LoginFilter implements Filter {
    private UserService userService = new UserService();
    private FreeMarker freeMarker = new FreeMarker();
    TemplateEngine engine ;
     private final Connection connection;


    public LoginFilter(Connection connection) {
     this.connection = connection;
    this.userService = new UserService(new DaoUserSql(connection));
     }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1;
        if (request instanceof HttpServletRequest) {
            request1 = (HttpServletRequest) request;
        } else {
            throw new IllegalArgumentException("HttpServletRequest error");
        }
        HashMap<String, Object> userData = new HashMap<>();
        if (HttpMethod.POST.name().equalsIgnoreCase(request1.getMethod())) {
            try {
                FromRequest fromRequest = new FromRequest(request1);
                String nickName = fromRequest.getParamString("Username");
                String password = fromRequest.getParamString("Password");

                System.out.printf("%s, %s",nickName,password);
                User user = new User(nickName, password);

                if (!userService.checkUsers(user)) {
                    System.out.println(user);
                    System.out.printf("%s, %s",nickName,password);
                    throw new Exception("Incorrect username or password");
                }

            } catch (Exception e) {
                e.printStackTrace();
                userData.put("Information", e.getMessage());
                userData.put("rout", "login");
                ((HttpServletResponse)response).sendRedirect("/login");
//                engine.render("fail.ftl", userData, (HttpServletResponse) response);
            }
            chain.doFilter(request,response);

        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
