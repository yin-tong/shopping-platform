<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">


    <title>注册</title>


    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <!-- Font Awesome -->
    <!-- Ionicons -->
    <!-- Theme style -->
    <!-- iCheck -->
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->

    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/iCheck/square/blue.css">
</head>

<body class="hold-transition login-page">
    <div class="login-box" style="border:5px solid gainsboro; background-color:#ffffff;">
        <div class="login-logo">

            <a href="all-admin-index.html"><b>内购</b>平台管理系统</a>

        </div>
        <!-- /.login-logo -->
        <div class="login-box-body">
            <p class="login-box-msg">注册</p>

            <form id="registerTableForm"  method="post">
                <div class="form-group has-feedback">
                    <input id="username" type="text" name="username" class="form-control" placeholder="用户名">
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input id="password" type="password" name="password" class="form-control" placeholder="密码">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input id="confirmPassword" type="password" name="confirmPassword" class="form-control" placeholder="请确认密码">
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input id="email" type="text" name="email" class="form-control" placeholder="邮箱">
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                </div>
                <div class="form-group has-feedback">
                    <input id="phoneNum" type="text" name="phoneNum" class="form-control" placeholder="联系电话">
                    <span class="glyphicon glyphicon-earphone form-control-feedback"></span>
                </div>
                <div class="row">
                    <div class="col-xs-4"></div>
                    <div class="col-xs-4 ">
                        <button  class="btn btn-primary btn-block btn-flat" onclick="register()">注册</button>
                    </div>
                    <!-- /.col -->
                </div>
            </form>
            <!-- /.social-auth-links -->

        </div>
        <!-- /.login-box-body -->
    </div>
    <!-- /.login-box -->

    <!-- jQuery 2.2.3 -->
    <!-- Bootstrap 3.3.6 -->
    <!-- iCheck -->
    <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/plugins/iCheck/icheck.min.js"></script>
    <script>
        $(function() {
            $('input').iCheck({
                checkboxClass: 'icheckbox_square-blue',
                radioClass: 'iradio_square-blue',
                increaseArea: '20%' // optional
            });
        });

        function register(){
            if ($('#username').val().length==0||$('#password').val().length==0||$('#confirmPassword').val().length==0||$('#email').val().length==0||$('#phoneNum').val().length==0){
                alert("用户名，密码，确认密码，邮箱，联系电话不能为空");
            }else{
                if ($('#password').val()==$('#confirmPassword').val()){
                    var registerTableForm = document.getElementById("registerTableForm");
                    registerTableForm.action="${pageContext.request.contextPath}/user/register";
                    registerTableForm.submit();//表单提交
                }else {
                    alert("密码与确认密码不一致，请重新填写");
                }
            }

        }
    </script>

    <style type="text/css">
        .login-page {
            background: url("http://localhost:8080/SMM02_train_web/img/img.png") no-repeat center center fixed;
            background-size: cover;
        }
    </style>
</body>

</html>