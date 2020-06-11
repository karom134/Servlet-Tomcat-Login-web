package sample.webapp.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.mysql.DatabaseConnector;
import sample.webapp.security.SecurityService;
import sample.webapp.security.UserService;
import sample.webapp.servlet.HomeServlet;
import sample.webapp.servlet.LoginServlet;

import java.util.ArrayList;
import java.util.List;

public class ServletRouter {

    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses=new ArrayList<>();

    {
        servletClasses.add(HomeServlet.class);
        servletClasses.add(LoginServlet.class);
        servletClasses.add(RegisterServlet.class);
    }

    public void init(Context ctx){
        UserService userService=new UserService();
        SecurityService securityService=new SecurityService();
        securityService.setUserService(userService);
        DatabaseConnector databaseConnector=new DatabaseConnector();

        for(Class<? extends AbstractRoutableHttpServlet> servletClass:servletClasses){
            try {
                AbstractRoutableHttpServlet httpServlet=servletClass.newInstance();
                httpServlet.setSecurityService(securityService);
                httpServlet.setDatabaseConnector(databaseConnector);
                Tomcat.addServlet(ctx,httpServlet.getClass().getName(),httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(),httpServlet.getClass().getName());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
