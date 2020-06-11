package sample.webapp.mysql;

import java.sql.*;

public class DatabaseConnector {

    public DatabaseConnector(){

    }

    private String getPassword(String input) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ooc_hw3","karomV","karom.140598");
        String query = "select * from user where Username=?";
        PreparedStatement pstmt = con.prepareStatement(query);
        pstmt.setString(1, input);
        ResultSet rs=pstmt.executeQuery();
        rs.next();
        return rs.getNString("password");
    }
    public static void main(String args[]) {
        DatabaseConnector databaseConnector=new DatabaseConnector();
        try {
            System.out.println(databaseConnector.getPassword("karomV"));
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
