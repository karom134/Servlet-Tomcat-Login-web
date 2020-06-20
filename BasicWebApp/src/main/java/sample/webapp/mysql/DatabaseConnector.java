package sample.webapp.mysql;

import org.mindrot.jbcrypt.BCrypt;
import sample.webapp.security.User;

import java.sql.*;

public class DatabaseConnector {
    private Connection con;
    public DatabaseConnector(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public Boolean checkUserExist(String username) throws SQLException {
        String query = "select * from user where Username=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs=pstmt.executeQuery();
        return rs.next();
    }

    public User generateUser(String name) throws ClassNotFoundException, SQLException {
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
        return user;
    }
    public void updateUser(String username,String str,String columnName) throws ClassNotFoundException, SQLException {
        String query=null;
        if(columnName.equals("name")) {
            query = "update user set name=? where Username=?";
        }
        else if(columnName.equals("password")){
            str= BCrypt.hashpw(str,BCrypt.gensalt(12));
            query = "update user set password=? where Username=?";
        }
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1,str);
        pstmt.setString(2,username);
        pstmt.executeUpdate();
    }
    public void addDatabase(String name,String username,String password) throws ClassNotFoundException, SQLException {
        String query="insert into user(Username, name, password) " +
                "values (?, ?, ?)";
        String str= BCrypt.hashpw(password,BCrypt.gensalt(12));
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, name);
        pstmt.setString(3, str);
        pstmt.executeUpdate();
    }
    public void removeUser(String username) throws ClassNotFoundException, SQLException {
        String query="delete from user where Username=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
    }

    public Connection getCon() {
        return con;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnector databaseConnector=new DatabaseConnector();
        databaseConnector.addDatabase("fabc","tester","tester");
        databaseConnector.removeUser("tester");
    }
}
