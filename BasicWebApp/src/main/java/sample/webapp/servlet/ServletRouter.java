package sample.webapp.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.mysql.DatabaseConnector;
import sample.webapp.security.SecurityService;
import sample.webapp.security.UserService;
import java.util.ArrayList;
import java.util.List;

public class ServletRouter {
    private DatabaseConnector databaseConnector;
    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses = new ArrayList<>();
    private List<String> urls = new ArrayList<>();

    {
        servletClasses.add(HomeServlet.class);
        servletClasses.add(LoginServlet.class);
        servletClasses.add(RegisterServlet.class);
        servletClasses.add(UserServlet.class);
        servletClasses.add(EditServlet.class);
    }

    public void init(Context ctx) {
        databaseConnector = new DatabaseConnector();
        UserService userService = new UserService(databaseConnector);
        SecurityService securityService = new SecurityService();
        securityService.setUserService(userService);


        for (Class<? extends AbstractRoutableHttpServlet> servletClass : servletClasses) {
            try {
                AbstractRoutableHttpServlet httpServlet = servletClass.newInstance();
                httpServlet.setSecurityService(securityService);
                urls.add(httpServlet.getPattern());
                Tomcat.addServlet(ctx, httpServlet.getClass().getName(), httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(), httpServlet.getClass().getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public DatabaseConnector getDatabaseConnector() {
        return databaseConnector;
    }
}
