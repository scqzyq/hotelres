
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>管理员登录</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">


    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>

</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" action="/admin/login" method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">管理员登录</h1>
            <img src="/images/login-logo.png" class="img-responsive" alt=""/>
        </div>
        <div class="login-wrap">
            <input type="text" class="form-control" name="aname" placeholder="用户名" autofocus>
            <input type="password" class="form-control" name="apassword" placeholder="密码">

            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>

        </div>
    </form>

</div>

</body>
</html>
