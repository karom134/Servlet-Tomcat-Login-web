package sample.webapp.servlet;

import sample.webapp.AbstractRoutableHttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class EditServlet extends AbstractRoutableHttpServlet {
    @Override
    public String getPattern() {
        return "/edit";
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            if (securityService.isAuthorize(req)) {
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/modification.jsp");
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
        String username= (String) req.getSession().getAttribute("edit");
        String val=(String) req.getSession().getAttribute("val");
        System.out.println(req.getParameter("name"));
        System.out.println(req.getParameter("confirmation"));
        if(req.getParameter("submit")!=null){
            if(!req.getParameter("name").equals(req.getParameter("confirmation"))){
                String error="Please try again,input not match";
                req.setAttribute("error",error);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("WEB-INF/modification.jsp");
                requestDispatcher.include(req, resp);
            }
            else {
                securityService.getUserService().modifyUser(username, req.getParameter("name"), val);
                if (val.equals("name")) {
                    resp.sendRedirect("/user");
                } else if (val.equals("password")) {
                    resp.sendRedirect("/");
                }
            }
        }
        else if(req.getParameter("back")!=null){
            if(val.equals("name")){
                resp.sendRedirect("/user");
            }else if(val.equals("password")){
                resp.sendRedirect("/");
            }
        }
    }
}
