package sample.webapp.security;

import sample.webapp.mysql.DatabaseConnector;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserService {
    private DatabaseConnector databaseConnector;

    public UserService(DatabaseConnector databaseConnector){
        this.databaseConnector=databaseConnector;
    }
    public User findUsername(String username) throws SQLException, ClassNotFoundException {
        return databaseConnector.generateUser(username);
    }

    public Boolean checkUser(String username) throws SQLException {
        return databaseConnector.checkUserExist(username);
    }
}
