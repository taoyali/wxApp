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
    <script type="text/javascript" src="../js/axios.min.js"></script>
    <script type="text/javascript" src="../js/cookieJS.js"></script>
    <script>
        function login() {
//            var username = getCookie("USER_INFO_LOGIN_NAME")
//            var userpwd = getCookie("USER_INFO_USER_PWD")
            var username = document.getElementById('userName').value
            var userpwd = document.getElementById('userPwd').value
            var data = { userName:username, userPwd:userpwd};

            axios.post('http://localhost:8080/LoginServlet', data)
                .then(function (response) {
                    if (response.status == 200) {
                        alert("请求成功");
                        setTimeout("javascript:location.href='/T_index.html'", 2000);
                    } else  {
                        alert("失败");
                        alert(response.errorCode);
                        alert(response.error);
                    }
                })
                .catch(function (error) {
                    alert(error);
                });
        }
    </script>
  </head>
  <body>
  <p>  taoyali </p>

  <form>
    name: <input type="text" name="name" id="userName" />
    <br />
    password: <input type="text" name="password" id="userPwd" />
    <input type="submit" value="submit" onclick="login()" />
  </form>

  <br />

  <form method="post">
    name: <input type="text" name="name" />
    <br />
    password: <input type="text" name="pwd" />
    <input type="submit" value="submit" />
  </form>

  <div>
    <a href="html/login_regist/regist.jsp"> 注册 </a>
  </div>
  <script>
//      var username = document.cookie.valueOf(USER_INFO_LOGIN_NAME)
//      var userpwd = sessionStorage.valueOf(USER_INFO_USER_PWD)
//      document.getElementById("userName").valueOf(username)
//      document.getElementById("userPwd").valueOf(userpwd)
  </script>
  </body>
</html>
