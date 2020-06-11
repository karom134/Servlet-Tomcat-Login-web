<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/11/2020
  Time: 9:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%
    String id = request.getParameter("id");
    String driver = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://localhost:3306/ooc_hw3";
    String userid = "karomV";
    String password = "karom.140598";
    try {
        Class.forName(driver);
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
%>
<html>
<body>
<p>
    ${message}
</p>
<form method="post">
<table border="1">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>action1</td>
        <td>action2</td>
    </tr>
    <%
        try{
            connection = DriverManager.getConnection(connectionUrl, userid, password);
            statement=connection.createStatement();
            String sql ="select * from user";
            resultSet = statement.executeQuery(sql);
            Integer i=0;
            while(resultSet.next()){
    %>
    <tr>
        <% String name=resultSet.getString("name");
            i++;
        %>
        <td><%=i %></td>
        <td><%=resultSet.getString("name") %></td>
        <td><button type="submit" name="remove" value= <%=resultSet.getString("Username")%>>Remove</button></td>
        <td><button type="submit" name="edit" value= <%=resultSet.getString("Username")%>>Edit</button></td>
    </tr>
    <%
            }
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    %>
</table>
    <button type="submit" name="back">Home</button>
    <button type="submit" name="add">Add User</button>
</form>
</body>
</html>
