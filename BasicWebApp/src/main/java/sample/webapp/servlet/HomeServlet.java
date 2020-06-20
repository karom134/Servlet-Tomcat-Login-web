package sample.webapp.servlet;



import sample.webapp.AbstractRoutableHttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

public class HomeServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            if (securityService.isAuthorize(req)) {
                String username = securityService.getCurrentUsername(req);
                req.setAttribute("username", username);


                Date date = new Date();
                req.setAttribute("date1", date);//key-value container
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/home.jsp");
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
        if(req.getParameter("logout")!=null) {
            securityService.logout(req);
            resp.sendRedirect("/");
        }
        else if(req.getParameter("user")!=null){
            resp.sendRedirect("/user");
        }
        else if(req.getParameter("change")!=null){
            req.getSession().setAttribute("val","password");
            req.getSession().setAttribute("edit",req.getSession().getAttribute("username"));
            resp.sendRedirect("/edit");
        }
    }

    @Override
    public String getPattern() {
        return "/index.jsp";
    }
}
