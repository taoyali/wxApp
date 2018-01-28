<%--
  Created by IntelliJ IDEA.
  User: taoyali
  Date: 2017/9/3
  Time: 下午8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <link rel="stylesheet" href="../../css/login_regist/regist.css">
</head>
<body>
<div id="login_container">
    <form id="login_form" action="../../RegistServlet" method="get">
        <p1> 注册 </p1>
        用户名： <input type="text" name="name" placeholder="" />
        密  码： <input type="password" name="pwd" />
        邮  箱： <input type="email" name="email" placeholder="方便我们之间的联系" />
        <input type="submit" value="注册" />
    </form>
</div>

</body>
</html>
