<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <link rel="shortcut icon" href="#" type="/image/png">

    <title>修改偏好信息</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-slider.min.css" rel="stylesheet">

    <script src="/js/modernizr.min.js"></script>


</head>

<body class="sticky-header">

<section>
    <!-- left side start-->
    <div class="left-side sticky-left-side">
        <iframe src="/left.jsp" height="100%" width="100%" scrolling="no" frameborder="0"></iframe>
    </div>
    <!-- left side end-->

    <!-- main content start-->
    <div class="main-content">

        <!-- header section start-->
        <div class="header-section">
            <iframe src="/top.jsp" height="50px" width="85%" scrolling="no" frameborder="0"></iframe>
        </div>
        <!-- header section end-->

        <!-- page heading start-->
        <div class="page-heading">
            <h3>
                修改偏好信息
            </h3>
        </div>
        <!-- page heading end-->

        <!--body wrapper start-->
        <section class="wrapper">
            <!-- page start-->

            <div class="row">
                <div class="col-sm-12">

                    <section class="panel">
                        <header class="panel-heading">
                            该系统将依据您设置的酒店偏好信息为您推荐酒店,请认真考虑后修改.
                        </header>
                    </section>

                    <section class="panel">
                        <div class="panel-body">
                            <form class="form-horizontal adminex-form" action="/user/updateInfo" method="post">
                                <div class="form-group">
                                    <label class="col-sm-2 control-label col-lg-2">请设置您所期望的酒店位置评分:</label>
                                    <div class="col-lg-10">

                                        <div class="well">
                                            <input id="jiudianweizhi" name="jiudianweizhi" type="text" data-slider-min="0" data-slider-max="5"
                                                   data-slider-step="0.1" data-slider-value="3"/>
                                            <span>评分为: <span
                                                    id="jiudianweizhiV">3</span></span>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label col-lg-2">请设置您所期望的卫生清洁评分:</label>
                                    <div class="col-lg-10">
                                        <div class="well">
                                            <input id="weishengqingjie" type="text" name="weishengqingjie" data-slider-min="0" data-slider-max="5"
                                                   data-slider-step="0.1" data-slider-value="3"/>
                                            <span>评分为: <span
                                                    id="weishengqingjieV">3</span></span>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label col-lg-2">请设置您所期望的设备设施评分:</label>
                                    <div class="col-lg-10">
                                        <div class="well">
                                            <input id="shebeisheshi" name="shebeisheshi" type="text" data-slider-min="0" data-slider-max="5"
                                                   data-slider-step="0.1" data-slider-value="3"/>
                                            <span>评分为: <span
                                                    id="shebeisheshiV">3</span></span>
                                        </div>
                                    </div>
                                    <label class="col-sm-2 control-label col-lg-2">请设置您所期望的服务质量评分:</label>
                                    <div class="col-lg-10">
                                        <div class="well">
                                            <input id="fuwuzhiliang" name="fuwuzhiliang" type="text" data-slider-min="0" data-slider-max="5"
                                                   data-slider-step="0.1" data-slider-value="3"/>
                                            <span >评分为: <span
                                                    id="fuwuzhiliangV">3</span></span>
                                        </div>
                                    </div>
                                </div>
                                <input class="btn btn-default" id="checkHotelInfo" style="float: right" type="submit" value="提交">
                            </form>
                        </div>
                    </section>


                </div>
            </div>

            <!-- page end-->
        </section>
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

<!--common scripts for all pages-->
<script src="/js/scripts.js"></script>
<script src="/js/jquery-2.1.0.min.js"></script>
<script src="/js/bootstrap-slider.min.js"></script>

<script>
    $(function () {
        var username = "${username}";
        if (username == null || username == ""){
            document.getElementById("checkHotelInfo").disabled=true;
        }else{
            document.getElementById("checkHotelInfo").disabled=false;
        }
        var flag = <%=request.getAttribute("f")%>;
        if (flag == 1){
            alert("修改成功!");
            <%
            request.removeAttribute("f");
            %>
            window.location.href="/reservation.jsp";
        }
        $("#jiudianweizhi").slider({});
        $("#jiudianweizhi").on("slide", function (slideEvt) {
            $("#jiudianweizhiV").text(slideEvt.value);
        });
        $("#weishengqingjie").slider({});
        $("#weishengqingjie").on("slide", function (slideEvt) {
            $("#weishengqingjieV").text(slideEvt.value);
        });
        $("#shebeisheshi").slider({});
        $("#shebeisheshi").on("slide", function (slideEvt) {
            $("#shebeisheshiV").text(slideEvt.value);
        });
        $("#fuwuzhiliang").slider({});
        $("#fuwuzhiliang").on("slide", function (slideEvt) {
            $("#fuwuzhiliangV").text(slideEvt.value);
        });
    })
</script>
</body>
</html>
