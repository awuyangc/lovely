/**
 * Created by wuyang on 2017/3/19.
 */
$(function(){
    $("#btnLogin").click(function() {
        //用户名
        var username = $("#username").val();
        var password = $("#password").val();
        var validateCode=$("#validateCode").val();
        $.ajax({
            url: "/login/check.action",
            type: "POST",
            data: {username: username, password: password,validateCode:validateCode},
            success: function (result) {
                var flag=""
                if (result=="") {
                    window.location.href="forward/index.action";
                }
                else {
                    if(result=="1"){
                        flag="用户名未注册";
                        $("#usernameErr").text(flag)
                        $("#usernameErr").animate({opacity:1,right:10}, 350);
                    }
                    else if(result=="2"){
                        flag="密码不正确";
                        $("#passwordErr").text(flag)
                        $("#passwordErr").animate({opacity:1,right:10}, 350);

                    }
                    else if(result=="3"){
                        flag="账户已锁定";
                        $("#usernameErr").text(flag)
                        $("#usernameErr").animate({opacity:1,right:10}, 350);
                    }
                    else if(result=="4"){
                        flag="用户名或密码错误次数过多";
                    }
                    else if(result=="5"){
                        flag="验证码错误"
                        $("#validateCodeErr").text(flag)
                        $("#validateCodeErr").animate({opacity:1,right:100}, 350);
                    }
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState);
            }
        });
    });
    $("#password").focus(function(){
        $("#passwordErr").animate({opacity:0,right:0}, 250);
    })

    $("#username").focus(function(){
        $("#usernameErr").animate({opacity:0,right:0}, 250);
    })
    $("#validateCode").focus(function(){
        $("#validateCodeErr").animate({opacity:0,right:0}, 250);
    })

    $("#reloadValidateCode").click(function(){
        $("#validateCodeImg").attr("src","/login/validateCode.action?data=" + new Date() + Math.floor(Math.random()*24));
    })
});