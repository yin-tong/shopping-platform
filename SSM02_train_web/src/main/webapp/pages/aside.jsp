<%@ page import="com.ssm.service.IUserService" %>
<%@ page import="com.ssm.service.impl.UserService" %>
<%@ page import="com.ssm.domain.UserInfo" %>
<%@ page import="com.ssm.utils.MoneyUtils" %><%--
  Created by IntelliJ IDEA.
  User: asvs
  Date: 2021/5/16
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="${pageContext.request.contextPath}/img/tole1.JPG" class="img-circle" alt="User Image">
            </div>
            <div class="pull-left info">
                <p id="username"><security:authentication property="principal.username"/></p>
                <security:authorize access="hasRole('USER')">
                <a href="#" id="money">余额:
                    <%
                        String username = request.getParameter("username");
                        System.out.println("------------------"+username);
                        System.out.println("------------------"+MoneyUtils.getMoney(username));
                        out.println(MoneyUtils.getMoney(username));
                    %>元</a>
                <button type="button" class="btn bg-olive btn-xs" onclick="addMoney()">充值</button>
                </security:authorize>
            </div>
        </div>


        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">菜单</li>

            <security:authorize access="hasRole('ADMIN')">

            <li id="admin-index"><a href="${pageContext.request.contextPath}/path/toMain"><i class="fa fa-dashboard"></i> <span>首页</span></a></li>

            <!-- 菜单 -->

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-pie-chart"></i> <span>基础数据</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li id="charts-chartjs">
                        <a href="${pageContext.request.contextPath}/product/findAll">
                            <i class="fa fa-circle-o"></i> 产品管理
                        </a>
                    </li>

                    <li id="charts-morris">
                        <a href="${pageContext.request.contextPath}/orders/findAll">
                            <i class="fa fa-circle-o"></i> 订单管理
                        </a>
                    </li>
                </ul>
            </li>

            <li class="treeview">
                <a href="#">
                    <i class="fa fa-folder"></i> <span>系统管理</span>
                    <span class="pull-right-container">
                        <i class="fa fa-angle-left pull-right"></i>
                    </span>
                </a>
                <ul class="treeview-menu">

                    <li id="admin-manager">
                        <a href="${pageContext.request.contextPath}/user/findAll">
                            <i class="fa fa-circle-o"></i> 用户管理
                        </a>
                    </li>

                    <li id="admin-1r">
                        <a href="${pageContext.request.contextPath}/role/findAll">
                            <i class="fa fa-circle-o"></i> 角色管理
                        </a>
                    </li>

                    <li id="admin-2">
                        <a href="${pageContext.request.contextPath}/permission/findAll">
                            <i class="fa fa-circle-o"></i> 资源权限管理
                        </a>
                    </li>

                    <li id="admin-3">
                        <a href="${pageContext.request.contextPath}/sysLog/findAll">
                            <i class="fa fa-circle-o"></i> 访问日志
                        </a>
                    </li>
                </ul>
            </li>
            </security:authorize>

            <security:authorize access="hasRole('USER')">
                <li id="admin-index"><a href="${pageContext.request.contextPath}/product/buy?username=<security:authentication property="principal.username"/>"> <i class="fa fa-circle-o"></i><span>购买商品</span></a></li>
                <li id="admin-index"><a href="${pageContext.request.contextPath}/orders/findByUsername?username=<security:authentication property="principal.username"/>"> <i class="fa fa-circle-o"></i><span>订单查看</span></a></li>
            </security:authorize>

            <li id="admin-index"><a href="${pageContext.request.contextPath}/user/personalInformation?username=<security:authentication property="principal.username"/>"> <i class="fa fa-circle-o"></i><span>个人中心</span></a></li>
            <!-- 菜单 /-->
        </ul>
    </section>
    <!-- /.sidebar -->
</aside>

<script>
    function addMoney(){
        var money = prompt("请输入增加的金额大小：");
        if (money!=null && money!=""){
            console.log(money);
            console.log($("#username").text());
            $.ajax({
                type : "POST",
                url : "${pageContext.request.contextPath}/user/addMoney",
                data: {
                    username:  $("#username").text(),
                    money: money,
                },
                dataType : "json",
                success : function () {
                    console.log("成功");
                    window.location.reload(true);
                },
                error: function () {
                    console.log("失败");
                    window.location.reload(true);
                }
            });
        }
    }
</script>
</body>
</html>
