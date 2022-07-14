<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">




    <title>产品管理</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">




    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">

  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/morris/morris.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datepicker/datepicker3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.theme.default.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/select2/select2.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-markdown/css/bootstrap-markdown.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/skins/_all-skins.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.skinNice.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-slider/slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
</head>

<body class="hold-transition skin-purple sidebar-mini">

    <div class="wrapper">

        <!-- 页面头部 -->
        <jsp:include page="hearder.jsp"/>
        <!-- 页面头部 /-->

        <!-- 导航侧栏 -->
        <jsp:include page="aside.jsp"/>
        <!-- 导航侧栏 /-->

        <!-- 内容区域 -->
        <!-- @@master = admin-layout.html-->
        <!-- @@block = content -->

        <div class="content-wrapper">

            <!-- 内容头部 -->
            <section class="content-header">
                <h1>
                    产品管理
                    <small>产品列表</small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="#"><i class="fa fa-dashboard"></i>基础数据</a></li>
                    <li><a href="#">产品管理</a></li>
                    <li class="active">产品列表</li>
                </ol>
            </section>
            <!-- 内容头部 /-->

            <!-- 正文区域 -->
            <section class="content">
                <!-- .box-body -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">列表</h3>
                    </div>

                    <div class="box-body">
                        <!-- 数据表格 -->
                        <div class="table-box">

                            <!--工具栏-->
                            <div class="pull-left">
                                <div class="form-group form-inline">
                                    <div class="btn-group">
                                        <button type="button" class="btn btn-default" title="新建" onclick="location.href='${pageContext.request.contextPath}/pages/product-add.jsp'"><i class="fa fa-file-o"></i> 新建</button>
                                        <button type="submit" class="btn btn-default" title="删除" onclick="deleteProduct();"><i class="fa fa-trash-o"></i> 删除</button>
                                        <button type="button" class="btn btn-default" title="刷新" onclick="location.href='${pageContext.request.contextPath}/product/findAll'"><i class="fa fa-refresh"></i> 刷新</button>
                                    </div>
                                </div>
                            </div>
                                <div class="box-tools pull-right">
                                    <div class="has-feedback">
                                        <input id = "inputName" name="name" type="text" class="form-control input-sm" placeholder="根据产品名称搜索" onkeydown="findProductByName()">
                                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                                    </div>
                                </div>
                            <!--工具栏/-->

                            <form id="productTableForm" method="post">
                            <!--数据列表-->
                            <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                                <thead>
                                    <tr>
                                        <th class="" style="padding-right:0px;">
                                            <input id="selall" type="checkbox" class="icheckbox_square-blue">
                                        </th>
                                        <th class="sorting_desc">编号</th>
                                        <th class="sorting_asc sorting_asc_disabled">产品名称</th>
                                        <th class="sorting_desc sorting_desc_disabled">生产地址</th>
                                        <th class="sorting">入库时间</th>
                                        <th class="text-center sorting">产品价格</th>
                                        <th class="text-center sorting">产品描述</th>
                                        <th class="text-center sorting">产品数量</th>
                                        <th class="text-center sorting">产品状态</th>
                                        <th class="text-center">操作</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${pageInfo.list}" var="product">
                                    <tr>
                                        <td><input name="productIds" type="checkbox"  value="${product.id}"></td>
                                        <td hidden>${product.id}</td>
                                        <td>${product.productNum}</td>
                                        <td>${product.productName}</td>
                                        <td>${product.cityName}</td>
                                        <td>${product.departureTimeStr}</td>
                                        <td class="text-center">￥${product.productPrice}</td>
                                        <td class="text-center">${product.productDesc}</td>
                                        <td class="text-center">${product.quantity}</td>
                                        <td class="text-center">${product.productStatusStr}</td>
                                        <td class="text-center">
                                            <button type="button" class="btn bg-olive btn-xs" onclick="location.href='${pageContext.request.contextPath}/product/show?productId=${product.id}'">编辑</button>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                                <!--
                            <tfoot>
                            <tr>
                            <th>Rendering engine</th>
                            <th>Browser</th>
                            <th>Platform(s)</th>
                            <th>Engine version</th>
                            <th>CSS grade</th>
                            </tr>
                            </tfoot>-->
                            </table>
                            <!--数据列表/-->
                            </form>

                        </div>
                        <!-- 数据表格 /-->
                    </div>
                    <!-- /.box-body -->

                    <!-- .box-footer-->
                    <div class="box-footer">
                        <div class="pull-left">
                            <div class="form-group form-inline">
                                总共${pageInfo.pages} 页，共${productCnt} 条数据。 每页
                                <select class="form-control" id="changePageSize" onchange="changePageSize()">
                                    <option>15</option>
                                    <option>5</option>
                                    <option>10</option>
                                    <option>20</option>
                                </select> 条
                            </div>
                        </div>

                        <div class="box-tools pull-right">
                            <ul class="pagination">
                                <li>
                                    <a href="${pageContext.request.contextPath}/product/findAll?page=1&size=${pageInfo.pageSize}" aria-label="Previous">首页</a>
                                </li>
                                <li><a href="${pageContext.request.contextPath}/product/findAll?page=${pageInfo.pageNum-1}&size=${pageInfo.pageSize}">上一页</a></li>

                                <c:forEach begin="1" end="${pageInfo.pages}" var="pageNum" >
                                    <li><a href="${pageContext.request.contextPath}/product/findAll?page=${pageNum}&size=${pageInfo.pageSize}">${pageNum}</a></li>
                                </c:forEach>

                                <li><a href="${pageContext.request.contextPath}/product/findAll?page=${pageInfo.pageNum+1}&size=${pageInfo.pageSize}">下一页</a></li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/product/findAll?page=${pageInfo.pages}&size=${pageInfo.pageSize}" aria-label="Next">尾页</a>
                                </li>
                            </ul>
                        </div>

                    </div>
                    <!-- /.box-footer-->



                </div>

            </section>
            <!-- 正文区域 /-->

        </div>
        <!-- @@close -->
        <!-- 内容区域 /-->

        <!-- 底部导航 -->
        <footer class="main-footer">
            <div class="pull-right hidden-xs">
                <b>Version</b> 1.0.8
            </div>
            <strong>Copyright &copy; 2014-2017 <a href="http://www.itcast.cn">研究院研发部</a>.</strong> All rights reserved.
        </footer>
        <!-- 底部导航 /-->

    </div>


    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jQueryUI/jquery-ui.min.js"></script>
    <script>
        $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/raphael/raphael-min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/morris/morris.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/knob/jquery.knob.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/moment.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/daterangepicker/daterangepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/slimScroll/jquery.slimscroll.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/fastclick/fastclick.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/adminLTE/js/app.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/treeTable/jquery.treetable.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/select2/select2.full.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/colorpicker/bootstrap-colorpicker.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.zh-CN.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/bootstrap-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/locale/bootstrap-markdown.zh.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-markdown/js/to-markdown.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ckeditor/ckeditor.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/input-mask/jquery.inputmask.extensions.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/datatables/dataTables.bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/chartjs/Chart.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.resize.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.pie.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/flot/jquery.flot.categories.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/ionslider/ion.rangeSlider.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-slider/bootstrap-slider.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap-datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
    <script>
        function changePageSize(){
            //获取下拉框的值
            var pageSize = $("#changePageSize").val();
            //向服务器端发送请求，改变显示条数
            location.href="${pageContext.request.contextPath}/product/findAll?page=1&size="+pageSize;
        }

        function deleteProduct(){
            var ids = document.getElementsByName("ids");
            var falg = false
            for (let i = 0; i < ids.length; i++) {
                if (ids[i].checked){
                    falg = true;
                    break;
                }
            }
            if(!falg){
                alert("删除选项不能为空！");
            }else {
                var productTableForm = document.getElementById("productTableForm");
                productTableForm.action="${pageContext.request.contextPath}/product/delete";
                productTableForm.submit();//表单提交
            }
        }

        function findProductByName(){
            var event = window.event || arguments.callee.caller.arguments[0];
            if (event.keyCode == 13)
            {
                var inputName = document.getElementById("inputName");
                var name = inputName.value;
                location.href="${pageContext.request.contextPath}/product/findByName?name="+name;

            }
        }

        $(document).ready(function() {
            // 选择框
            $(".select2").select2();

            // WYSIHTML5编辑器
            $(".textarea").wysihtml5({
                locale: 'zh-CN'
            });
        });


        // 设置激活菜单
        function setSidebarActive(tagUri) {
            var liObj = $("#" + tagUri);
            if (liObj.length > 0) {
                liObj.parent().parent().addClass("active");
                liObj.addClass("active");
            }
        }


        $(document).ready(function() {

            // 激活导航位置
            setSidebarActive("admin-datalist");

            // 列表按钮 
            $("#dataList td input[type='checkbox']").iCheck({
                checkboxClass: 'icheckbox_square-blue',
                increaseArea: '20%'
            });
            // 全选操作 
            $("#selall").click(function() {
                var clicks = $(this).is(':checked');
                if (!clicks) {
                    $("#dataList td input[type='checkbox']").iCheck("uncheck");
                } else {
                    $("#dataList td input[type='checkbox']").iCheck("check");
                }
                $(this).data("clicks", !clicks);
            });
        });
    </script>
</body>

</html>