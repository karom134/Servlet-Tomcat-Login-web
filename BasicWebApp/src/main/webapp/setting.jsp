<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 6/2/2020
  Time: 4:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Setting page</title>
</head>
<body>
<h2>
    This is where the content of setting page.
</h2>
<h3>
    Time now is: <%
        Date date=new Date();
        out.println(date);
    %>
</h3>
</body>
</html>
