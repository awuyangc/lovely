<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12
  Time: 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台管理首页</title>
    <!--css区域-->
    <meta name="renderer" content="webkit"/>
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/system/index.css" rel="stylesheet">
</head>
<body>
<div class="container-fluid">
    <div class="row"  style="padding:30px;background-color:darkseagreen;">
        <div class="col-md-12">
            <div style="color:white">欢迎访问后台管理系统</div>
        </div>
    </div>
    <div class="row" style="height:90%">
        <div class="col-xs-2 text-center" style="border-right:dashed;padding:20px;height:100%">
            <ul class="nav nav-list">
                <li class="active">
                    <a href="#" class="menuButton" data-id="instruction.action">介绍</a>
                </li>
                <li>
                    <a href="#" class="menuButton" data-id="manage.action">题库管理</a>
                </li>
                <li>
                    <a href="#" class="menuButton" data-id="vote.action">待解决问题</a>
                </li>
            </ul>
        </div>
        <div class="col-xs-10" style="height:100%">
            <iframe id="rightFrame" src="" style="width:100%;height:100%;padding-top:10px" frameborder="0"></iframe>
        </div>
    </div>
</div>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/system/index.js"></script>
</body>
</html>
