package sample.webapp.mysql;

import sample.webapp.security.User;

import java.sql.*;

public class DatabaseConnector {

    public DatabaseConnector(){

    }
    public Boolean checkUserExist(String username) throws SQLException {
        String query = "select * from user where Username=?";
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs=pstmt.executeQuery();
        return rs.next();
    }
    public User generateUser(String name) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
        String query = "select * from user where Username=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, name);
        ResultSet rs=pstmt.executeQuery();
        User user=null;
        if(rs.next()) {
            user = new User(rs.getNString("Username"),
                    rs.getNString("password"),
                    rs.getNString("name"));
        }
        con.close();
        return user;
    }
    public void updateUser(String username){

    }
    public void addDatabase(String name,String username,String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
        String query="insert into user(Username, name, password) " +
                "values (?, ?, ?)";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, name);
        pstmt.setString(3, password);
        pstmt.executeUpdate();
        con.close();
    }
    public void removeUser(String username) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
        String query="delete from user where Username=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
        con.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector databaseConnector=new DatabaseConnector();
        databaseConnector.removeUser("tester2");
    }
}
