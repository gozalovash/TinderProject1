package utils;

import models.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FreeMarkerByShams extends HttpServlet {

    private TemplateEngine engine;

    public FreeMarkerByShams(TemplateEngine engine) {
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User();

        //i Will change to take usernames from database
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);

        engine.render("user.html", data, resp);
    }
}
