<%--
  Created by IntelliJ IDEA.
  User: wuyang
  Date: 2017/1/15
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>看图答题</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- head 中 -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
  </head>

  <div class="pages">

  </div>

  <footer class="footer navbar-fixed-bottom">
    <div class="container">
      <div class="row" style="padding-bottom:40px">
        <div class="col-xs-6 text-center divBefore"><a class="btn btn-default beforeBtn hidden"  href="#" role="button">上一页</a></div>
        <div class="col-xs-6 text-center divNext"><a class="btn btn-default nextBtn"  href="#" role="button">下一页</a></div>
        <div class="col-xs-6 text-center divComplete hidden"><a class="btn btn-success completeBtn"  href="#" role="button">完成</a></div>
        <input id="pageFlag" type="hidden" value="1"/>
      </div>
    </div>
  </footer>
  <body>

  <!--js区域-->
  <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="//cdn.bootcss.com/fastclick/1.0.6/fastclick.min.js"></script>
  <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
  <script type="text/javascript" src="/js/index.js"></script>
  </body>
</html>
