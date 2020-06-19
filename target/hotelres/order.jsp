<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="/image/png">

    <title>订单记录</title>

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">
    <link href="/css/bootstrap-table.min.css" rel="stylesheet">

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
            <div class="header-section">
                <iframe src="/top.jsp" height="50px" width="85%" scrolling="no" frameborder="0"></iframe>
            </div>
            <!--notification menu end -->

        </div>
        <!-- header section end-->
        <section>
            <!-- page heading start-->
            <div class="page-heading">
                <h3>
                    订单记录
                </h3>
            </div>
            <!-- page heading end-->

            <!--body wrapper start-->

            <table id="mytab"></table>
            <div id="toolbar" class="btn-group">
                <button id="btn_delete" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>
        </section>

        <!--body wrapper end-->


    </div>
    <!-- main content end-->
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="/js/jquery-1.10.2.min.js"></script>
<script src="/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="/js/jquery-migrate-1.2.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/modernizr.min.js"></script>
<%--<script src="/js/jquery.nicescroll.js"></script>--%>
<script src="/js/bootstrap-table/bootstrap-table.min.js"></script>
<script src="/js/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>

<!--common scripts for all pages-->
<script src="/js/scripts.js"></script>

<script>

    $(function () {
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();
    });

    $('#mytab').bootstrapTable({
        method: 'get',
        url: "/order/findOrders",//请求路径
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页
        pagination: true,//是否分页,
        pageSize: 5,//单页记录数
        pageList : [ 5, 10, 20, 30 ],
        showRefresh: false,//刷新按钮
        sidePagination: 'client',//server:服务器端分页|client：前端分页
        search:true,
        toolbar:'#toolbar',
        uniqueId:'oid',

        columns: [{
            checkbox:true,
        },{
            title: '订单编号',
            field: 'oid',
            sortable: true
        }, {
            title: '姓名',
            field: 'contactName',
        }, {
            title: '酒店',
            field: 'hotelName',
        }, {
            title: '房间类型',
            field: 'roomType',
        }, {
            title: '入住时间',
            field: 'checkin',
        }, {
            title: '离店时间',
            field: 'checkout',
        }, {
            title: '总价',
            field: 'totalPrice',
        }, {
            title: '下单时间',
            field: 'orderTime',
        }]
    });

    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {

            $("#btn_delete").click(function () {
                var arrselections = $("#mytab").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    alert("请选择有效数据！");
                    return;
                }
                if (!window.confirm("确认要删除选择的数据吗？")) {
                    return;
                }
                var oid = new Array();// 声明一个数组
                $(arrselections).each(function() {// 通过获得别选中的来进行遍历
                    oid.push(this.oid);// cid为获得到的整条数据中的一列
                });
                $.ajax({
                    type: "post",
                    url: "/order/removeOrder",
                    data: "oid="+oid,
                    success: function (data) {
                        if (data == "success") {
                            alert('删除成功！');
                            $("#mytab").bootstrapTable('refresh');
                        }
                    },
                    error: function () {
                        alert('删除失败！');
                    },
                    complete: function () {
                    }
                });
            });
        };
        return oInit;
    };

</script>
</body>
<!--footer section start-->
<footer>
    2020 &copy; By Scqzyq
</footer>
<!--footer section end-->
</html>
