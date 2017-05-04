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
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
    <!-- head 中 -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
  </head>

  <div class="page" id="page1">
    <div class="container-fluid">
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-12"><image src="files/1.jpg"  class="img-thumbnail"></image></div>
      </div>
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-4"><input type="radio" name="radio1" value="A">A</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="B">B</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="C">C</div>
      </div>
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-4"><input type="radio" name="radio1" value="D">D</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="E">E</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="F">F</div>
      </div>
    </div>
  </div>
  <div class="page hidden" id="page2">
    <div class="container-fluid">
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-12"><image src="files/1.jpg"  class="img-thumbnail"></image></div>
      </div>
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-4"><input type="radio" name="radio1" value="A">1</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="B">2</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="C">3</div>
      </div>
      <div class="row" style="padding-bottom:20px">
        <div class="col-xs-4"><input type="radio" name="radio1" value="D">4</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="E">5</div>
        <div class="col-xs-4"><input type="radio" name="radio1" value="F">6</div>
      </div>
    </div>
  </div>

  <footer class="footer navbar-fixed-bottom">
    <div class="container">
      <div class="row" style="padding-bottom:10%">
        <div class="col-xs-6 text-center"><a class="btn btn-default fowardBtn"  href="#" role="button">上一页</a></div>
        <div class="col-xs-6 text-center"><a class="btn btn-default fowardBtn"  href="#" role="button">下一页</a></div>
        <input id="pageFlag" type="hidden" value="page1"/>
      </div>
    </div>
  </footer>
  <body>

  <!--js区域-->
  <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
  <script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="/js/index.js"></script>
  </body>
</html>
