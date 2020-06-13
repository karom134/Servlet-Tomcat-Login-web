<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/11/2020
  Time: 11:52 PM
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
<form method="post">
    <label for="name"><b>${val}</b></label>
    <input type="text" placeholder="Enter Value" name="name" required>
    <br>
    <label for="name"><b>confirmation</b></label>
    <input type="text" placeholder="Enter Value" name="confirmation" required>
    <br>
    <button type="submit" name="submit">Submit</button>
</form>
<form method="post">
    <button type="submit" name="back">Back</button>
</form>
</body>
</html>
