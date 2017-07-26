/**
 * Created by Administrator on 2017/5/12.
 */
$(function(){
    $("#rightFrame").attr("src","instruction.action");
    $(".menuButton").click(function(){
        event.preventDefault();
        if($(this).parent()!=$(".nav-list .active")){
            $(".nav-list .active").removeClass("active");
            $(this).parent().addClass("active");
        }
        $("#rightFrame").attr("src", $(this).data("id"));
    });
})