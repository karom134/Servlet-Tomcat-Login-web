package sample.webapp.security;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Objects;

public class SecurityService {

    private UserService userService;

    public UserService getUserService() {
        return userService;
    }

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
        boolean isMatch= BCrypt.checkpw(password,userService.findUsername(user).getPassword());
        if(isMatch){
            HttpSession session = req.getSession();
            session.setAttribute("username",user);
            return true;
        }
        else{
            return false;
        }
    }
}
