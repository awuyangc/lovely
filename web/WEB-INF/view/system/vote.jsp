<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/12
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <!--css区域-->
    <meta name="renderer" content="webkit"/>
    <link href="//cdn.datatables.net/1.10.15/css/jquery.dataTables.min.css" rel="stylesheet">
    <link href="//cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.css">
    <link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/system/vote.css" rel="stylesheet">
    <style>
        .dataTables_wrapper .dataTables_paginate{
            text-align:center;
            float:none;
        }
        .dataTables_wrapper .dataTables_info{
            text-align:right;
            float:none;
        }
        .dataTables_wrapper .dataTables_paginate .paginate_button:hover{

        }
        table td{
            text-align:center;
        }
        table th{
            text-align:center;
        }
        label{
            font-weight: normal;
        }
        .dataTables_length{
            padding-top:0.755em;
        }
    </style>
</head>
<body>
<table id="example" class="display" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>question_id</th>
        <th>图片路径</th>
        <th>答案A</th>
        <th>答案B</th>
        <th>答案C</th>
        <th>答案D</th>
        <th>答案E</th>
    </tr>
    </thead>
</table>

<!-- Modal -->
<div class="modal bs-example-modal-sm" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">请选择您认为正确的答案！</h4>
            </div>
            <div class="modal-body" id="radioOtherArea">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btnSaveVoteAnswer">设为正确答案</button>
            </div>
        </div>
    </div>
</div>
<!--js区域-->
<script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdn.datatables.net/1.10.15/js/jquery.dataTables.min.js"></script>
<script src="//cdn.datatables.net/plug-ins/28e7751dbec/integration/bootstrap/3/dataTables.bootstrap.js"></script>
<script type="text/javascript" src="/js/system/vote.js"></script>
</body>
</html>
