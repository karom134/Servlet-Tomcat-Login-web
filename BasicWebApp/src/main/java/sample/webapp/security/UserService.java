package sample.webapp.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private Map<String,User> users=new HashMap<>();
    {
        users.put("karom-V",new User("karom-V","12345"));
        users.put("karom-V-2",new User("karom-V-2","12345"));
    }
    public User findUsername(String username){
        return users.get(username);
    }

    public Boolean checkUser(String username){
        return users.containsKey(username);
    }
}
