<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>用户注册</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">

    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>


    <script>
        var f1 = "";
        var f2 = "";
        var f3 = "";
        function checkPd() {
            var pw1 = document.getElementById("password").value;
            var pw2 = document.getElementById("password1").value;
            if (pw1 != pw2) {
                document.getElementById("tips").innerHTML = "两次密码不相同";
                f1+="两次密码不相同\n";
            } else {
                document.getElementById("tips").innerHTML = "";
                f1 = "";
            }
        }

        function checkIdcard() {
            var idcard = document.getElementById("idcard").value;
            $.post("/user/checkIdcard", {"idcard": idcard}, function (data) {
                if (data == "yes") {
                    document.getElementById("error").innerHTML = "该身份证已注册";
                    f2+="该身份证已注册\n";
                } else {
                    document.getElementById("error").innerHTML = "";
                    f2 = "";
                }
            });

        }

        function checkUsername() {
            var username = document.getElementById("username").value;
            $.post("/user/checkUsername", {"username": username}, function (data) {
                if (data == "yes") {
                    document.getElementById("error1").innerHTML = "该用户名已注册";
                    f3+="该用户名已注册\n";
                } else {
                    document.getElementById("error1").innerHTML = "";
                    f3 = "";
                }
            })

        }
    </script>

</head>

<body class="login-body">

<div class="container">

    <form class="form-signin" id="regform" action="/user/saveUser" method="post" onsubmit="return checkInput();">
        <div class="form-signin-heading text-center">
            <h1 class="sign-title">用户注册</h1>
            <img src="images/login-logo.png" class="img-responsive" alt=""/>
        </div>


        <div class="login-wrap">
            <p> 请输入本人身份信息</p>
            <input type="text" autofocus="" required="required"
                   id="idcard" name="idcard" placeholder="身份证号" onblur="checkIdcard()" class="form-control"><span
                id="error" style="color: red"></span>
            <input type="text" autofocus="" required="required" name="realname" placeholder="真实姓名" class="form-control">
            <input type="text" autofocus="" required="required" name="tel" placeholder="联系电话" class="form-control">

            <p> 请输入注册账号信息</p>
            <input type="text" autofocus="" required="required" id="username" name="username" placeholder="用户名"
                   onblur="checkUsername()" class="form-control"><span id="error1" style="color: red"></span>
            <input type="password" required="required" id="password" name="password"
                   οnkeyup="value=value.replace(/[^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{6,18}$]/ig,'')" placeholder="密码"
                   class="form-control">
            <p><input type="password" required="required" id="password1" placeholder="再次确认密码" onkeyup="checkPd()"
                      class="form-control"><span id="tips" style="color: red"></span></p>

            <button type="submit" id="submit" class="btn btn-lg btn-login btn-block">
                <i class="fa fa-check"></i>
            </button>

            <div class="registration">
                已注册?点此
                <a href="/login.jsp" class="">
                    登录
                </a>
            </div>

        </div>

    </form>

</div>

<script>
    // 验证手机号
    function isPhoneNo(phone) {
        var pattern = /^1[34578]\d{9}$/;
        return pattern.test(phone);
    }

    // 验证身份证
    function isCardNo(card) {
        var pattern = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
        return pattern.test(card);
    }

    // 验证函数
    function formValidate() {
        var str = '';


        // 判断手机号码

        if (isPhoneNo($.trim($('#tel').val()) == false)) {
            str += '手机号码不正确\n';
            $('#tel').focus();
        }


        // 验证身份证

        if (isCardNo($.trim($('#idcard').val())) == false) {
            str += '身份证号格式不正确\n';
            $('#idcard').focus();
        }


        // 如果没有错误则提交
        if (str != ''||f1 != ''||f2 != ''||f3 != '') {
            alert(f1+f2+f3+str);
            return false;
        } else {
            $('#regform').submit();
        }
    }

    function checkInput() {
        return formValidate();
    }
</script>

</body>
</html>
