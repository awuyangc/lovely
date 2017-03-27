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
    <title>知识库</title>
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/index.css" rel="stylesheet">
  </head>
  <body>
  <nav class="navbar wy-navbar">
      <div class="wy-header">
        <img class="logo_small" src="/images/logo_small.png"/>
        <span style="position:relative;width:400px">
        <input class="wy-search" type="text" autocomplete="off" value="" maxlength="100" placeholder="搜索你感兴趣的内容..." aria-autocomplete="list">
        <img class="searchImg" src="/images/search32.ico">
        </span>
        <nav class="wy-header-nav">
          <a href="/forward/index.action">首页</a>
          <a href="#">发现</a>
          <a href="#">话题</a>
        </nav>
      </div>
  </nav>
  <div>
    首页${sessionScope.currentUser.user_name}
  </div>

  </body>
</html>
