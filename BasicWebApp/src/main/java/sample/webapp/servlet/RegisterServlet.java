package sample.webapp.servlet;

import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.security.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class RegisterServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("submit")!=null){
            submitClick(req,resp);
        }
        else if(req.getParameter("back")!=null) {
            resp.sendRedirect("/user");
        }
    }
    private void submitClick(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String reenter_password=req.getParameter("reenter_password");
        UserService userService=securityService.getUserService();
        if(userService.checkUser(username)){
            String error="This Username is already exist.";
            req.setAttribute("error",error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
            requestDispatcher.include(req, resp);
        }
        else if(!password.equals(reenter_password)){
            String error="Please try again,password not match";
            req.setAttribute("error",error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
            requestDispatcher.include(req, resp);
        }
        else{
            userService.addUser(name,username,password);
            resp.sendRedirect("/login");
        }
    }
    @Override
    public String getPattern() {
        return "/register";
    }
}
