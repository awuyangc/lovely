<%--
  Created by IntelliJ IDEA.
  User: wuyang
  Date: 2017/1/15
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <!--css区域-->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
</head>
<body>
<canvas style="position:absolute;z-index:-1" id="canvas"></canvas>
<div class="container-fluid">
    <div class="text-right" style="padding-top: 10px;">
        <a href="">注册</a>
    </div>
    <div class="row" style="height:15%;"></div>
    <div class="row" style="height:60%">
        <div class="col-md-4"></div>
        <div class="col-md-4 text-center">
            <!--登录窗口-->
            <div class="maintitle"></div>
            <div class="subtitle">与他人分享你的知识、经验和见解</div>
            <div style="display:inline-table;width:300px">
                    <div class="form-group"><input id="username" type="text" class="form-control" placeholder="用户名"></div>
                    <div class="form-group"><input id="password" type="password" class="form-control" placeholder="密码"></div>
                    <div class="form-group"><button id="btnLogin" class="btn  btn-block btnLogin">登录</button></div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row" style="height:15%"></div>
    <div>

    </div>
</div>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/canvas.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>
