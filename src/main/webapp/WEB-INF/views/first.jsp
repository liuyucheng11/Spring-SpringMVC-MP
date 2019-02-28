<%--
  Created by IntelliJ IDEA.
  User: liuyucheng
  Date: 2019-2-19
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<%
    request.setAttribute("name", "小明");
    request.setAttribute("age", 20);
%>
<jsp:forward page="second.jsp"></jsp:forward>
</body>
</html>
