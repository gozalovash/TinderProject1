package utils;
/*
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
        for(User user:){

        //i Will change to take usernames from database
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);

        engine.render("user.html", data, resp);
    }
}*/
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

    public class FreeMarkerByShams {

        public void getHtmlPage(Map<String, Object> map, HttpServletResponse resp, String htmlFile) throws IOException {
            Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
            cfg.setDirectoryForTemplateLoading(new File("./content"));
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setLogTemplateExceptions(false);
            cfg.setWrapUncheckedExceptions(true);

            Template template = cfg.getTemplate(htmlFile);
            PrintWriter writer = resp.getWriter();

            try {
                template.process(map, writer);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
    }
