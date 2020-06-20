package sample.webapp.servlet;



import sample.webapp.AbstractRoutableHttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class LoginServlet extends AbstractRoutableHttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/login.jsp");
        requestDispatcher.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try {
                loginClick(req,resp);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }
    private void loginClick(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException, SQLException, ClassNotFoundException {
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
