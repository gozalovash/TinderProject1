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

public class LoginFilter implements Filter {
    private UserService userService;
    private FreeMarker freeMaker = new FreeMarker();
    private final Connection connection;
    HashMap<String, Object> userData = new HashMap<>();

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

        if (HttpMethod.POST.name().equalsIgnoreCase(request1.getMethod())) {
            try {
                FromRequest fromRequest = new FromRequest(request1);
                String nickName = fromRequest.getParamString("Nickname");
                String password = fromRequest.getParamString("password");
                User user = new User(nickName, password);

                if(!userService.checkUsers(user)){
                     throw  new Exception("Incorrect nickname or password");
                }

            }  catch (Exception e) {
                userData.put("Information",e.getMessage());
                userData.put("rout","login");
                freeMaker.render("MAKE IT shams", userData,(HttpServletResponse) response );
            }

        }
        else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
