<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/11/2020
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    ${error}
</p>
<p>
<form method="post">
    <label for="name"><b>Name</b></label>
    <input type="text" placeholder="Enter Name" name="name" required>
    <br>
    <label for="username"><b>Username</b></label>
    <input type="text" placeholder="Enter Username" name="username" required>
    <br>
    <label for="password"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="password" required>
    <br>
    <label for="password2"><b>Reenter Password</b></label>
    <input type="password" placeholder="Enter Password" name="reenter_password" required>
    <br>
    <br>
    <button type="submit" name="submit">Submit</button>
</form>
<form method="post">
    <button type="submit" name="back">Back</button>
</form>
</p>
</body>
</html>
