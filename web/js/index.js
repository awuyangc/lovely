/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    // window.onscroll=function(){
    //     var sl=-Math.max(document.body.scrollLeft,document.documentElement.scrollLeft);
    //     document.getElementById('wy-navbar').style.left=sl+'px';
    // }
    $("#headmenu").click(function(e){
        stopPropagation(e);
        var objDownMenu=$(".downMenu");
        if(objDownMenu.hasClass("hiddenMenu")){
            objDownMenu.removeClass("hiddenMenu");
            objDownMenu.animate({opacity:1,top:51}, 250);
        }
        else{
            objDownMenu.animate({opacity:0,top:45}, 250,function(){
                objDownMenu.addClass("hiddenMenu");
            });
        }
    });

    //停止冒泡，注：js默认先child 后 parent
    function stopPropagation(e) {
        if (e.stopPropagation)
            e.stopPropagation();//停止冒泡  非ie
        else
            e.cancelBubble = true;//停止冒泡 ie
    }
    $(document).bind('click',function(){
        var objDownMenu=$(".downMenu");
        objDownMenu.animate({opacity:0,top:45}, 250,function(){
            objDownMenu.addClass("hiddenMenu");
        });
    });


});