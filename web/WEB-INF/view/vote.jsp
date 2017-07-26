<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投票</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/vote.css" rel="stylesheet">
</head>
<body>
<div class="pages">

</div>

<div id="voteHtml" class="hidden">
    <div class="container-fluid">
        <div class="row" style="padding-bottom:20px">
            <div class="col-xs-5" id="chkA"></div>
            <div class="col-xs-7" style="display:flex"><div id="progressA" class="wy-progress" style="background-color:red;"></div><div id="ticketA"></div></div>
        </div>
        <div class="row" style="padding-bottom:20px">
            <div class="col-xs-5" id="chkB"></div>
            <div class="col-xs-7" style="display:flex"><div id="progressB" class="wy-progress" style="background-color:orange;"></div><div id="ticketB"></div></div>
        </div>
        <div class="row" style="padding-bottom:20px">
            <div class="col-xs-5" id="chkC"></div>
            <div class="col-xs-7" style="display:flex"><div id="progressC" class="wy-progress" style="background-color:yellow;"></div><div id="ticketC"></div></div>
        </div>
        <div class="row" style="padding-bottom:20px">
            <div class="col-xs-5" id="chkD"></div>
            <div class="col-xs-7" style="display:flex"><div id="progressD" class="wy-progress" style="background-color:green;"></div><div id="ticketD"></div></div>
        </div>
        <div class="row" style="padding-bottom:20px">
            <div class="col-xs-5" id="chkE"></div>
            <div class="col-xs-7" style="display:flex"><div id="progressE" class="wy-progress" style="background-color:greenyellow;width: 0%"></div><div id="ticketE"></div></div>
        </div>
    </div>
</div>

<footer class="footer navbar-fixed-bottom">
    <div class="container">
        <div class="row" style="padding-bottom:20px" id="footContent">
            <div class="col-xs-4 text-center divBefore"><a class="btn btn-default beforeBtn hidden"  href="#" role="button">上一页</a></div>
            <div class="col-xs-4 text-center divComplete"><a class="btn btn-success voteBtn"  href="#" role="button">投票</a></div>
            <div class="col-xs-4 text-center divNext"><a class="btn btn-default nextBtn"  href="#" role="button">下一页</a></div>
            <input id="pageFlag" type="hidden" value="1"/>
        </div>
    </div>
</footer>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/fastclick/1.0.6/fastclick.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="/js/vote.js"></script>

</body>
</html>
