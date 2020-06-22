package sample.webapp.servlet;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.mysql.DatabaseConnector;
import sample.webapp.security.SecurityService;
import sample.webapp.security.UserService;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServletRouter implements Filter {
    private DatabaseConnector databaseConnector;
    private final List<Class<? extends AbstractRoutableHttpServlet>> servletClasses = new ArrayList<>();
    private static Map<String, HttpServlet> urls = new HashMap<>();

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
                Tomcat.addServlet(ctx, httpServlet.getClass().getName(), httpServlet);
                ctx.addServletMapping(httpServlet.getPattern(), httpServlet.getClass().getName());
                urls.put(httpServlet.getPattern(), httpServlet);
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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String url = req.getRequestURI();
        if(!urls.containsKey(url)){
            resp.sendRedirect("/home");
        }
        else{
            urls.get(url).service(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
