<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>修改密码</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">

    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>



    <script>

        function checkPd() {
            var pw1 = document.getElementById("password").value;
            var pw2 = document.getElementById("password1").value;
            if(pw1 != pw2) {
                document.getElementById("tips").innerHTML="两次密码不相同";
                document.getElementById("submit").disabled = true;
            }else {
                document.getElementById("tips").innerHTML="";
                document.getElementById("submit").disabled = false;
            }

        }
    </script>

</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" action="/user/updatePw" method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">修改密码</h1>
            <img src="../../images/login-logo.png" class="img-responsive" alt=""/>
        </div>


        <div class="login-wrap">

            <p> ${username}您好,请修改您的密码!</p>
            <input type="password" id="password" name="password" placeholder="密码" class="form-control">
            <p><input type="password" id="password1" placeholder="再次确认密码" onkeyup="checkPd()" class="form-control"><span id="tips" style="color: red"></span></p>

            <button type="submit" id="submit" class="btn btn-lg btn-login btn-block">
                <i class="fa fa-check"></i>
            </button>

        </div>

    </form>

</div>



</body>
</html>
