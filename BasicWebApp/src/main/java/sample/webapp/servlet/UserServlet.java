package sample.webapp.servlet;

import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.security.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;


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
        User user=null;
        try {
            user=securityService.getUserService().findUsername(req.getParameter("remove"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if(req.getParameter("back")!=null){
            resp.sendRedirect("/");
        }
        else if(req.getParameter("remove")!=null){
            HttpSession session=req.getSession();
            System.out.println(session.getAttribute("username"));
            if(!session.getAttribute("username").equals(user.getUsername())) {
                String message = "User " + user.getName() + " has been removed.";

                req.setAttribute("message", message);
                securityService.getUserService().removeUser(user.getUsername());
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/userpage.jsp");
                requestDispatcher.include(req, resp);
            }
            else{
                String message = "You can't remove yourself.";

                req.setAttribute("message", message);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/userpage.jsp");
                requestDispatcher.include(req, resp);
            }
        }
        else if(req.getParameter("add")!=null){
            resp.sendRedirect("/register");
        }
        else if(req.getParameter("edit")!=null){
            req.getSession().setAttribute("edit",req.getParameter("edit"));
            req.getSession().setAttribute("val","name");
            resp.sendRedirect("/edit");
        }
    }
}
