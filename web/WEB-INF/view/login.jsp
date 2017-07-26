<%--
  Created by IntelliJ IDEA.
  User: wuyang
  Date: 2017/1/15
  Time: 11:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>登录</title>
    <!--css区域-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row" style="padding-top:30px;">
        <div class="col-xs-12 text-center">
            <div class="page-header">
                <h4 >建议在wifi环境下使用，土豪请随意！</h4>
            </div>
        </div>
    </div>
    <div class="row" style="height:100px;position: absolute;margin: auto;top: 0;bottom: 0;">
        <div class="col-xs-2"></div>
        <div class="col-xs-4 text-right"><input type="button" id="loginWX"  class="btn btn-success" value="微信登录"></div>
        <div class="col-xs-4 text-left"><input type="button" id="loginYK" class="btn" value="游客登录"></div>
        <div class="col-xs-2"></div>
    </div>

</div>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>
