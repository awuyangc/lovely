/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    // window.onscroll=function(){
    //     var sl=-Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
    //     document.getElementById('wy-navbar').style.left=sl+'px';
    // }
    $("#logOut").click(function(){
        window.location.href="/forward/logout.action";
    });
    $("#headmenu").click(function(){
        var objDownMenu=$(".downMenu");
        if(objDownMenu.hasClass("hiddenMenu")){
            objDownMenu.removeClass("hiddenMenu");
            objDownMenu.animate({opacity:1,top:51}, 250);

        }
        else{
            objDownMenu.addClass("hiddenMenu");
            objDownMenu.animate({opacity:0,top:45}, 250);
        }

    });


});