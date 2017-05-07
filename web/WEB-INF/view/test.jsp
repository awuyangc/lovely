<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/5/5
  Time: 9:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试页面</title>
    <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#addTestData").click(function(){
                $.ajax({
                    url: "/test/addTestData.action",
                    type: "POST",
                    data: {param: "添加测试数据"},
                    success: function (result) {

                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        alert(XMLHttpRequest.readyState);
                    }
                });
            })
        });

    </script>
</head>
<body>
<input id="addTestData" type="button" value="添加测试数据">
</body>
</html>
