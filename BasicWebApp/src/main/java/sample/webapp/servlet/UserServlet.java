package sample.webapp.servlet;

import sample.webapp.AbstractRoutableHttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class UserServlet extends AbstractRoutableHttpServlet {
    @Override
    public String getPattern() {
        return "/user";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (securityService.isAuthorize(req)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/userpage.jsp");
                requestDispatcher.include(req, resp);
            }
            else{
                resp.sendRedirect("/login");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("back")!=null){
            resp.sendRedirect("/");
        }
        else if(req.getParameter("remove")!=null){
            System.out.println(req.getParameter("remove"));
        }
        else if(req.getParameter("add")!=null){
            resp.sendRedirect("/register");
        }
    }
}
