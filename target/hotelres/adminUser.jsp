<%--
  Created by IntelliJ IDEA.
  User: scqzy
  Date: 2020/4/26
  Time: 上午9:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>后台管理</title>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <link rel="shortcut icon" href="#" type="/image/png">

    <link href="/css/style.css" rel="stylesheet">
    <link href="/css/style-responsive.css" rel="stylesheet">
    <link href="/css/bootstrap-table.min.css" rel="stylesheet">
</head>
<body class="sticky-header">

<section>

    <div class="left-side sticky-left-side">
        <iframe src="/leftAdmin.jsp" height="100%" width="100%" scrolling="no" frameborder="0"></iframe>
    </div>

    <!-- main content start-->
    <div class="main-content">

        <section>


            <!--body wrapper start-->

            <table id="mytab"></table>

            <div id="toolbar" class="btn-group">
                <button id="btn_add" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
                </button>
                <button id="btn_delete" type="button" class="btn btn-default">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
                </button>
            </div>

            <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal"
                 class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title">新增数据</h4>
                        </div>
                        <div class="modal-body">
                            <p>请输入用户信息:</p>
                            <input type="text" name="id" id="id" placeholder="用户编号" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="username" id="username" placeholder="用户名" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="password" id="password" placeholder="密码" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="tel" id="tel" placeholder="联系方式" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="idcard" id="idcard" placeholder="身份证号" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="realname" id="realname" placeholder="真实姓名" autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="jiudianweizhi" id="jiudianweizhi" placeholder="酒店位置评分"
                                   autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="weishengqingjie" id="weishengqingjie" placeholder="卫生清洁评分"
                                   autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="shebeisheshi" id="shebeisheshi" placeholder="设备设施评分"
                                   autocomplete="off"
                                   class="form-control placeholder-no-fix">
                            <input type="text" name="fuwuzhiliang" id="fuwuzhiliang" placeholder="服务质量评分"
                                   autocomplete="off"
                                   class="form-control placeholder-no-fix">
                        </div>
                        <div class="modal-footer">
                            <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
                            <button class="btn btn-primary" type="button" id="btn_submit">提交</button>
                        </div>
                    </div>
                </div>
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
        var options = {
            editFiled: 1,
        };
        var oButtonInit = new ButtonInit();
        oButtonInit.Init();
    });

    $('#mytab').bootstrapTable({
        method: 'get',
        url: "/user/findAll",//请求路径
        striped: true, //是否显示行间隔色
        pageNumber: 1, //初始化加载第一页
        pagination: true,//是否分页,
        pageSize: 10,//单页记录数
        pageList: [5, 10, 20, 30],
        showRefresh: false,//刷新按钮
        sidePagination: 'client',//server:服务器端分页|client：前端分页
        search: true,
        toolbar: '#toolbar',
        editable: true,
        uniqueId: "id",
        clickToSelect: true,
        columns: [{
            checkbox: true,
            field: "check",
        }, {
            title: '编号',
            field: 'id',
            sortable: true
        }, {
            title: '用户名',
            field: 'username',
        }, {
            title: '密码',
            field: 'password',
        }, {
            title: '联系方式',
            field: 'tel',
        }, {
            title: '身份证号',
            field: 'idcard',
        }, {
            title: '真实姓名',
            field: 'realname',
        }, {
            title: '酒店位置',
            field: 'jiudianweizhi',
        }, {
            title: '卫生清洁',
            field: 'weishengqingjie',
        }, {
            title: '设备设施',
            field: 'shebeisheshi',
        }, {
            title: '服务质量',
            field: 'fuwuzhiliang',
        }],

        onDblClickCell: function (field, value, row, $element) {
            var upIndex = $element[0].parentElement.rowIndex - 1;

            $element[0].innerHTML = "<input id='inputCell' type='text' name='inputCell' style ='width: auto' value='" + value + "'>";
            $("#inputCell").focus();
            $("#inputCell").blur(function () {
                var newValue = $("#inputCell").val();
                row[field] = newValue;
                // alert(field);
                $(this).remove();
                $('#mytab').bootstrapTable('updateCell', {
                    index: upIndex,
                    field: field,
                    value: newValue
                });
                updateRow(row);
                // updateExbEnt();
            });
        },
    });

    function updateRow(row) {
        var url = "/user/updateUser";
        var config = {
            url: url,
            type: "post",
            dataType: "json",
            data: row,
        };
        $.ajax(config);
    }

    var ButtonInit = function () {
        var oInit = new Object();
        var postdata = {};

        oInit.Init = function () {
            $("#btn_add").click(function () {
                $('#myModal').modal();
            });


            $("#btn_delete").click(function () {
                var arrselections = $("#mytab").bootstrapTable('getSelections');
                if (arrselections.length <= 0) {
                    alert("请选择有效数据！");
                    return;
                }
                if (!window.confirm("确认要删除选择的数据吗？")) {
                    return;
                }
                var id = new Array();// 声明一个数组
                $(arrselections).each(function () {// 通过获得别选中的来进行遍历
                    id.push(this.id);// cid为获得到的整条数据中的一列
                });
                $.ajax({
                    type: "post",
                    url: "/user/removeUser",
                    data: "id=" + id,
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
            $("#btn_submit").click(function () {
                postdata.id = $("#id").val();
                postdata.username = $("#username").val();
                postdata.password = $("#password").val();
                postdata.tel = $("#tel").val();
                postdata.idcard = $("#idcard").val();
                postdata.realname = $("#realname").val();
                postdata.jiudianweizhi = $("#jiudianweizhi").val();
                postdata.weishengqingjie = $("#weishengqingjie").val();
                postdata.shebeisheshi = $("#shebeisheshi").val();
                postdata.fuwuzhiliang = $("#fuwuzhiliang").val();
                console.log(postdata);
                $.ajax({
                    type: "post",
                    url: "/user/addUser",
                    data: postdata,
                    success: function (data) {
                        if (data == "success") {
                            alert("添加成功！");
                            $("#mytab").bootstrapTable('refresh');
                            $("#myModal").modal('hide');
                        }
                    },
                    error: function () {
                        alert("添加失败！");
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
</html>
