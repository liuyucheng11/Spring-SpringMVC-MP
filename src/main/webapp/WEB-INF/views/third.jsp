<%--
  Created by IntelliJ IDEA.
  User: liuyucheng
  Date: 2019-2-19
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" uri="/WEB-INF/tags/mytag.tld" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
     <%
         out.print("这是第三个页面");
     %>
     <mytag:hello name="自定义标签" ></mytag:hello>
</body>
</html>
