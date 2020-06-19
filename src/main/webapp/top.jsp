<%--
  Created by IntelliJ IDEA.
  User: scqzy
  Date: 20-3-19
  Time: 下午8:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="/image/png">

    <!--icheck-->
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/js/iCheck/skins/minimal/minimal.css" rel="stylesheet">
    <link href="/js/iCheck/skins/square/square.css" rel="stylesheet">
    <link href="/js/iCheck/skins/square/red.css" rel="stylesheet">
    <link href="/js/iCheck/skins/square/blue.css" rel="stylesheet">

    <!--dashboard calendar-->
    <link href="/css/clndr.css" rel="stylesheet">


    <!--common-->
    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">


    <script src="/js/jquery-1.10.2.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <script src="/js/modernizr.min.js"></script>
    <script>
        var un;
        $(function () {
            un="${sessionScope.username}";
            if (un == null||un == "") {
                document.getElementById("info").innerHTML = "";
                return;
            }

        });


    </script>


</head>
<body style="background-color: #fff;">
<div>

    <!--notification menu start -->

    <div class="menu-right">
        <ul class="notification-menu" id="info">
            <li>
                <a href="/user/logout" target="_parent" class="btn btn-default dropdown-toggle">注销登录</a>
            </li>

            <li>
                <a href="#" class="btn btn-default dropdown-toggle">
                    ${username}
                </a>
            </li>

        </ul>
    </div>
    <!--notification menu end -->
</div>
<!-- Placed js at the end of the document so the pages load faster -->
<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/modernizr.min.js"></script>
<script src="/js/jquery.nicescroll.js"></script>

<!--easy pie chart-->
<script src="/js/easypiechart/jquery.easypiechart.js"></script>
<script src="/js/easypiechart/easypiechart-init.js"></script>

<!--Sparkline Chart-->
<script src="/js/sparkline/jquery.sparkline.js"></script>
<script src="/js/sparkline/sparkline-init.js"></script>

<!--icheck -->
<script src="/js/icheck-init.js"></script>
<script src="/js/iCheck/jquery.icheck.js"></script>

<!-- jQuery Flot Chart-->
<script src="/js/flot-chart/jquery.flot.js"></script>
<script src="/js/flot-chart/jquery.flot.tooltip.js"></script>
<script src="/js/flot-chart/jquery.flot.resize.js"></script>
<script src="/js/flot-chart/jquery.flot.pie.resize.js"></script>
<script src="/js/flot-chart/jquery.flot.selection.js"></script>
<script src="/js/flot-chart/jquery.flot.stack.js"></script>
<script src="/js/flot-chart/jquery.flot.time.js"></script>
<script src="/js/main-chart.js"></script>

<!--common scripts for all pages-->
<script src="/js/scripts.js"></script>
</body>
</html>
