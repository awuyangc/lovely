/**
 * Created by wuyang on 2017/3/19.
 */
$(function(){
    $("#btnLogin").click(function() {
        //用户名
        var username = $("#username").val();
        var password = $("#password").val();
        $.ajax({
            url: "/login/check.action",
            type: "POST",
            data: {username: username, password: password},
            success: function (result) {
                if (result=="") {
                    window.location.href="forward/index.action";
                }
                else {
                    alert(result);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState);
            }
        });
    });
});