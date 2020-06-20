package sample.webapp.security;

import sample.webapp.mysql.DatabaseConnector;

import java.sql.SQLException;


public class UserService {
    private DatabaseConnector databaseConnector;

    public UserService(DatabaseConnector databaseConnector){
        this.databaseConnector=databaseConnector;
    }
    public User findUsername(String username) throws SQLException, ClassNotFoundException {
        return databaseConnector.generateUser(username);
    }

    public Boolean checkUser(String username){
        try {
            return databaseConnector.checkUserExist(username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    public void removeUser(String username){
        try {
            databaseConnector.removeUser(username);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void addUser(String name,String username,String password){
        try {
            databaseConnector.addDatabase(name,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void modifyUser(String username,String modification,String colnane){
        try {
            databaseConnector.updateUser(username,modification,colnane);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
