<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="mytag" uri="http://www.yucheng.liu.com"%>
<html>
<head>
    <title>Title</title>
    <h2>hello this is page2</h2>
</head>

<body>
<%
    String name = (String) request.getAttribute("name");
    Integer age = (Integer) request.getAttribute("age");
    out.print("Name:" + name+"\n\r");
    out.print("Age:" + age+"\n\r");
    out.print("URI："+request.getRequestURI()+"\n\r");
    out.print("URL" + request.getRequestURL()+"\n\r");

/*

    response.sendRedirect("./thirdPage.do");
*/
%>
  <mytag:hello name="hhhh"/>
   <p>stringList:</p> <mytag:listForEach list="${stringList}"/>
   <p>intList:</p><mytag:listForEach list="${intList}"/>
    <br/>
</body>
</html>
