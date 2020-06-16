package sample.webapp.servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("bobo");
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) request;
        String url="/"+req.getRequestURI().split("/")[1];
        List<String> urls=new ArrayList<>();
        urls.add("/");
        urls.add("/login");
        urls.add("/user");
        urls.add("/register");
        urls.add("/edit");
        System.out.println(url);
        HttpSession session=req.getSession();
        if(!urls.contains(url) && session.getAttribute("usernamr")!=null){
            resp.sendRedirect("/");
        }
        else{
            resp.sendRedirect(url);
        }
    }

    @Override
    public void destroy() {

    }
}
