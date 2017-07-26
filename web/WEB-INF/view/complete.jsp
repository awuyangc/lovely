<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/5
  Time: 11:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结果</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <!-- head 中 -->
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <!--<link href="/css/complete.css" rel="stylesheet">-->
</head>
<body>

<div class="container-fluid">
    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 text-center">
            <div id="scoreArea"></div>
        </div>
    </div>
    <div class="row" style="margin-top:20px">
        <div class="col-xs-12 text-center">
            <input type="button" id="btnDetail" class="btn btn-danger" value="查看详情(答错的题目)">
        </div>
    </div>
    <div class="row" style="margin-top:20px">
        <div id="detailArea"></div>
    </div>
</div>

<!-- Modal -->
<div class="modal bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">请选择您认为正确的答案并说明理由！</h4>
            </div>
            <div class="modal-body" id="radioOtherArea">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btnSaveOtherAnswer">提交</button>
            </div>
        </div>
    </div>
</div>

<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/fastclick/1.0.6/fastclick.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/js/complete.js"></script>
</body>
</html>
