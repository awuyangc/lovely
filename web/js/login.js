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
                    $("#passwordErr").text(result)
                    $("#passwordErr").animate({opacity:1,right:10}, 350);
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState);
            }
        });
    });
    $("#password").click(function(){
        $("#passwordErr").animate({opacity:0,right:0}, 250);
    })
});