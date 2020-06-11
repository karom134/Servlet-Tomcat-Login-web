package sample.webapp.servlet;



import sample.webapp.AbstractRoutableHttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("login")!=null){
            loginClick(req,resp);
        }
        else if(req.getParameter("register")!=null){
            resp.sendRedirect("/register");
        }
    }
    private void loginClick(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        if(securityService.login(req)){
            resp.sendRedirect("/");
        }else{
            String error="Please try again,invalid username or password";

            req.setAttribute("error",error);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
            requestDispatcher.include(req, resp);
        }
    }
    @Override
    public String getPattern() {
        return "/login";
    }
}
