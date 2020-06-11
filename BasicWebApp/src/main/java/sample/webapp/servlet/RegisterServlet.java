package sample.webapp.servlet;

import sample.webapp.AbstractRoutableHttpServlet;
import sample.webapp.security.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        String reenter_password=req.getParameter("reenter_password");
        System.out.println(password);
        System.out.println(reenter_password);
        if(!password.equals(reenter_password)){
            String error="Please try again,password not match";
            req.setAttribute("error",error);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/register.jsp");
            requestDispatcher.include(req, resp);
        }
        else{
            UserService userService=securityService.getUserService();
            userService.addUser(name,username,password);
            resp.sendRedirect("/login");
        }
    }

    @Override
    public String getPattern() {
        return "/register";
    }
}
