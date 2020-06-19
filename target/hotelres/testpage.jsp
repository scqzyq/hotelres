<%--
  Created by IntelliJ IDEA.
  User: scqzy
  Date: 2020/4/23
  Time: 下午2:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>
    <script>
        function getname() {
            var username = "${username}";
            alert(username);
            $.post(
                "/analyse/gethotelname", {"username": username}, function (data) {
                    document.getElementById("test").innerHTML = data;
                }
            )
        }
    </script>
</head>
<body>
<h2 id="test"></h2>
<input type="button" onclick="getname()" value="yuyue">
<a href="/find/All" >dianji</a>
</body>
</html>
