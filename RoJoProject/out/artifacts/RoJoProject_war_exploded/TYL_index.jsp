<%--
  Created by IntelliJ IDEA.
  User: taoyali
  Date: 2017/5/7
  Time: 下午3:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <p>  taoyali </p>

  <form action="MyLogin" method="get">
    name: <input type="text" name="name" />
    <br />
    password: <input type="text" name="password" />
    <input type="submit" value="submit" />
  </form>

  <br />

  <form action="RegistServlet" method="post">
    name: <input type="text" name="name" />
    <br />
    password: <input type="text" name="pwd" />
    <input type="submit" value="submit" />
  </form>

  <div>
    <a href="html/login_regist/regist.jsp"> 注册 </a>
  </div>
  </body>
</html>
