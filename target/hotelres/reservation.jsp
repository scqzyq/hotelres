<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

    <link rel="shortcut icon" href="#" type="/image/png">

    <title>酒店预订</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/fonts/css/font-awesome.min.css" rel="stylesheet">
    <link href="/css/bootstrap-slider.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="http://jqueryui.com/resources/demos/style.css">


    <script src="/js/modernizr.min.js"></script>


</head>

<body class="sticky-header">

<section class="col-sm-12">
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
                酒店预订
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
                            您的各项酒店偏好信息的分数为:
                        </header>
                    </section>

                    <section class="panel">
                        <div class="panel-body">
                            <div class="form-group">
                                <label class="col-sm-1 control-label col-lg-1">酒店位置:</label>
                                <div class="col-lg-1"><span id="jdwz"></span></div>
                                <label class="col-sm-1 control-label col-lg-1">卫生清洁:</label>
                                <div class="col-lg-1"><span id="wsqj"></span></div>
                                <label class="col-sm-1 control-label col-lg-1">设备设施:</label>
                                <div class="col-lg-1"><span id="sbss"></span></div>
                                <label class="col-sm-1 control-label col-lg-1">服务质量:</label>
                                <div class="col-lg-1"><span id="fwzl"></span></div>
                            </div>
                        </div>
                    </section>
                    <section class="panel">
                        <div class="panel-body">
                            <form action="/analyse/res" onsubmit="return checkTime()">
                                <div class="form-group col-md-12">
                                    <label class="control-label col-md-2 panel-heading">请选择入住日期:</label>
                                    <div class="col-md-2 col-xs-11">
                                        <input type="text" class="form-control" id="datepicker_in" name="datein">
                                    </div>
                                </div>
                                <div class="form-group col-md-12">
                                    <label class="control-label col-md-2 panel-heading">请选择离店日期:</label>
                                    <div class="col-md-2 col-xs-11">
                                        <input type="text" class="form-control" id="datepicker_out" name="dateout">
                                    </div>
                                </div>
                                <input class="btn btn-default" style="float: right" id="checkHotelInfo" type="submit"
                                       value="点击预订">
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
<script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<script>
    $(function () {
        $("#datepicker_in").datepicker({minDate: new Date(), dateFormat: "yy-mm-dd"});
        $("#datepicker_out").datepicker({minDate: new Date(), dateFormat: "yy-mm-dd"});
        var username = "${username}";
        if (username == null || username == "") {
            document.getElementById("checkHotelInfo").disabled = true;
        } else {
            document.getElementById("checkHotelInfo").disabled = false;
        }
        $.post("user/getScore", {"username": username}, function (data) {
            document.getElementById("jdwz").innerHTML = data.jiudianweizhi;
            document.getElementById("wsqj").innerHTML = data.weishengqingjie;
            document.getElementById("sbss").innerHTML = data.shebeisheshi;
            document.getElementById("fwzl").innerHTML = data.fuwuzhiliang;
        })
    });

    function checkTime() {
        var timein = document.getElementById("datepicker_in").value;
        var timeout = document.getElementById("datepicker_out").value;
        if (timein == "" || timein == null || timeout == "" || timeout == null) {
            alert("请选择合适的入住和离店时间!");
            return false;
        }
        timein.replace("-", "/");
        timeout.replace("-", "/");
        timein = new Date(Date.parse(timein));
        timeout = new Date(Date.parse(timeout));
        if (timein >= timeout) {
            alert("请选择合适的入住和离店时间!");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
