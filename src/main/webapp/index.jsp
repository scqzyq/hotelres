<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="keywords"
          content="admin, dashboard, bootstrap, template, flat, modern, theme, responsive, fluid, retina, backend, html5, css, css3">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="/image/png">

    <title>首页</title>

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


</head>

<body class="sticky-header">


<section>
    <div class="left-side sticky-left-side">
        <iframe src="/left.jsp" height="100%" width="100%" scrolling="no" frameborder="0"></iframe>
    </div>

    <!-- main content start-->
    <div class="main-content">

        <!-- header section start-->
        <div class="header-section">
            <iframe src="/top.jsp" height="50px" width="85%" scrolling="no" frameborder="0"></iframe>
        </div>
        <!-- header section end-->


        <!--body wrapper start-->
        <div class="wrapper">

        </div>
        <!--body wrapper end-->

        <!--footer section start-->
        <footer>
            2020 &copy; By Scqzyq
        </footer>
        <!--footer section end-->


    </div>
    <!-- main content end-->
</section>

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
<script src="/js/iCheck/jquery.icheck.js"></script>
<script src="/js/icheck-init.js"></script>

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
<script language="javascript" type="text/javascript">
    window.location.href="/reservation.jsp";</script>

</body>
</html>
