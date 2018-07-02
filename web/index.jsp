<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/6/27
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>测试servlet</title>
  </head>
  <body>
    <center>
    <a href="first?name='zhangsan'&sex='男'&password='123'">get请求</a>
    <br/>
    <form action="first" method="post">
      姓名：<input type="text" name = "name" value=""/>
      密码： <input type="tex" name="password" value="/">
      性别:<input type = "text" name = "sex" value = ""/>
      <input type="submit" value = "submit"/>
    </form>
    </center>
  </body>
</html>
