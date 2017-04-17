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
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/login.css" rel="stylesheet">
    <script type="text/javascript">
        var DEFAULT_VERSION = "8.0";
        var ua = navigator.userAgent.toLowerCase();
        var isIE = ua.indexOf("msie")>-1;
        var safariVersion;
        if(isIE){
            safariVersion =  ua.match(/msie ([\d.]+)/)[1];
            if(safariVersion <= DEFAULT_VERSION ){
                window.location.href="forward/iecheck.action";
            }
        }
    </script>
</head>
<body>
<canvas style="position:absolute;z-index:-1" id="canvas"></canvas>
<div class="container-fluid">
    <div class="text-right" style="padding-top: 10px;">
        <input type="button" id="btnRegister" class="btn btn-primary" data-toggle="modal" value="注册"></input>
    </div>
    <div class="row" style="height:15%;"></div>
    <div class="row" style="height:60%">
        <div class="col-md-4"></div>
        <div class="col-md-4 text-center">
            <!--登录窗口-->
            <div class="maintitle"></div>
            <div class="subtitle">与他人分享你的知识、经验和见解</div>
            <div style="display:inline-table;width:300px">
                    <div class="form-group" style="position:relative">
                        <input id="username" type="text" class="form-control" placeholder="用户名">
                        <label id="usernameErr" style="opacity:0;" class="usernameError">用户名错误</label>
                    </div>
                    <div class="form-group" style="position:relative">
                        <input id="password" type="password" class="form-control" placeholder="密码">
                        <label id="passwordErr" style="opacity:0;" class="passwordError">密码错误</label>
                    </div>
                <div class="form-group" style="position:relative">
                    <input id="validateCode" type="text" class="form-control" placeholder="验证码">
                    <label id="validateCodeErr" style="opacity:0;" class="validateCodeError">验证码错误</label>
                    <a href="#" id="reloadValidateCode"><img id="validateCodeImg" class="validateCodeImg" src="/login/validateCode.action"/></a>
                </div>
                    <div class="form-group">
                        <button id="btnLogin" class="btn  btn-block btnLogin">登录</button>
                    </div>
            </div>
        </div>
        <div class="col-md-4"></div>
    </div>
    <div class="row" style="height:15%"></div>
    <div>

    </div>
</div>

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title lead" id="myModalLabel">
                    新用户注册
                </h4>
            </div>
            <div class="modal-body  text-center">
                <div style="display:inline-table;width:300px">
                    <div class="form-group" style="position:relative">
                        <input id="registerName" type="text" class="form-control" placeholder="用户名">
                        <label id="registerNameErr" style="opacity:0;" class="usernameError">用户名错误</label>
                    </div>
                    <div class="form-group" style="position:relative">
                        <input id="registerPassword" type="password" class="form-control" placeholder="密码">
                        <label id="registerPasswordErr" style="opacity:0;" class="passwordError">密码错误</label>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                </button>
                <button type="button" class="btn btn-primary">
                    注册知识库
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/canvas.js"></script>
<script type="text/javascript" src="/js/login.js"></script>
</body>
</html>
