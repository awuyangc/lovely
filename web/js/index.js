/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
  //抽取10道题
    $(".fowardBtn").click(function(){
        event.preventDefault();
        var currentPage=$("#pageFlag").val();
        $("#"+currentPage).addClass("hidden");
        $("#"+(currentPage+1)).removeClass("hidden");
    })


});