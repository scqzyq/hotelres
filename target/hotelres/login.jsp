<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>用户登录</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">


    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>

    <script>
        $(function () {
            var f = <%=request.getAttribute("error")%>;
            if (f) {
                document.getElementById("err").hidden = false;
            }
        });

        function checkUserInfo() {
            var idcard = document.getElementById("idcard").value;
            var realname = document.getElementById("realname").value;

            $.post("/user/checkUserInfo", {"idcard": idcard, "realname": realname}, function (data) {
                if (data == "yes") {
                    alert("用户信息填写错误!");
                } else {
                    window.open("/updatePw.jsp");
                }
            })
        }

    </script>
</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" action="/user/login" method="post">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">用户登录</h1>
            <img src="/images/login-logo.png" class="img-responsive" alt=""/>
        </div>
        <div class="login-wrap">
            <p id="err" class="loginNote" hidden>用户名或密码错误,请重新输入!</p>
            <input type="text" class="form-control" name="username" placeholder="用户名" autofocus>
            <input type="password" class="form-control" name="password" placeholder="密码">

            <button class="btn btn-lg btn-login btn-block" type="submit">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                没有账号?点此
                <a class="" href="/registration.jsp">
                    注册
                </a>
            </div>
            <label class="checkbox">
                <span class="pull-left">
                    <a href="/adminLogin.jsp">管理员登录</a>
                </span>
                <span class="pull-right">
                    <a data-toggle="modal" href="#myModal"> 忘记密码</a>
                </span>
            </label>

        </div>
    </form>

    <!-- Modal -->
    <%--    <form onsubmit="checkUserInfo()" method="post">--%>
    <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">忘记密码</h4>
                </div>
                <div class="modal-body">
                    <p>请输入您的身份证号和真实姓名重置密码</p>
                    <input type="text" name="idcard" id="idcard" placeholder="身份证号" autocomplete="off"
                           class="form-control placeholder-no-fix">
                    <input type="text" name="realname" id="realname" placeholder="真实姓名" autocomplete="off"
                           class="form-control placeholder-no-fix">
                </div>
                <div class="modal-footer">
                    <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                    <button class="btn btn-primary" type="button" onclick="checkUserInfo()">提交</button>
                </div>
            </div>
        </div>
    </div>
    <!-- modal -->

    <%--    </form>--%>

</div>

</body>
</html>
