package sample.webapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Objects;

public class SecurityService {

    private UserService userService;


    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Boolean isAuthorize(HttpServletRequest req) throws SQLException {
        String username=getCurrentUsername(req);
        return userService.checkUser(username);
    }

    public String getCurrentUsername(HttpServletRequest req){
        HttpSession session=req.getSession();
        Object usernameObject=session.getAttribute("username");
        return (String) usernameObject;
    }

    public void logout(HttpServletRequest req){
        HttpSession session=req.getSession();
        session.removeAttribute("username");
        session.invalidate();
    }

    public boolean login(HttpServletRequest req) throws SQLException, ClassNotFoundException {
        String user=req.getParameter("username");
        String password=req.getParameter("password");
        if(userService.checkUser(user)){
            User userObject = userService.findUsername(user);
            HttpSession session = req.getSession();
            session.setAttribute("username",user);
            return Objects.equals(userObject.getPassword(),password);
        }
        else{
            return false;
        }
    }
}
