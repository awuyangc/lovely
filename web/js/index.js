/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    $("#pageFlag").val("1");
    FastClick.attach(document.body);
    var url= location.href.split('#')[0];
    $.ajax({
        url: "/wechat/JSSDK_Config.action",
        type: "POST",
        data: {url: url},
        success: function (result) {
            wx.config({
                debug: false,
                appId: result.appId,
                timestamp: result.timestamp,
                nonceStr: result.nonceStr,
                signature:result.signature,
                jsApiList: [
                    'checkJsApi',
                    'onMenuShareTimeline',
                    'onMenuShareAppMessage',
                    'onMenuShareQQ',
                    'onMenuShareWeibo',
                    'hideMenuItems',
                    'showMenuItems'
                ]
            });
        }
    });

    //参数设置
    //总题数
    var totalQuestion=10;
    //开始抽题
    $.ajax({
        url: "/core/getQuestionList.action",
        type: "POST",
        data: {total: totalQuestion},
        success: function (result) {

            var style="hidden";
            //提升dom性能
            $(".pages").css("display","none");
            $(".pages").html("");
            var pageHtml="";
            result.forEach(function(e,i){
                if(i==0){
                   style="";
                }
                else{
                    style="hidden";
                }
                pageHtml +='<div class="page '+style+'" id="page'+(i+1)+'">' +
                    '<div class="container-fluid">' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-12 text-center"><image src="/files/'+e.imgUrl+'" id="image'+(i+1)+'" data-question-id="'+e.id+'" class="img-thumbnail"></image></div>' +
                    '</div>' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'A" name="radio'+(i+1)+'" value="A"><label for="radio'+(i+1)+'A">'+e.chkA+'</label></div>' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'B" name="radio'+(i+1)+'" value="B"><label for="radio'+(i+1)+'B">'+e.chkB+'</label></div>' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'C" name="radio'+(i+1)+'" value="C"><label for="radio'+(i+1)+'C">'+e.chkC+'</label></div>' +
                    '</div>' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'D" name="radio'+(i+1)+'" value="D"><label for="radio'+(i+1)+'D">'+e.chkD+'</label></div>' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'E" name="radio'+(i+1)+'" value="E"><label for="radio'+(i+1)+'E">'+e.chkE+'</label></div>' +
                    '<div class="col-xs-4"><input type="radio" id="radio'+(i+1)+'F" name="radio'+(i+1)+'" value="F"><label for="radio'+(i+1)+'F">'+e.chkF+'</label></div>' +
                    '</div>' +
                    '<div class="row" style="">' +
                    '<div class="navbar-fixed-bottom text-center">第 '+(i+1)+' 页，共 '+totalQuestion+' 页</div>'+
                    '</div>'+
                    '</div>' +
                    '</div>';
            })
            $(".pages").append(pageHtml);
            $(".pages").css("display","");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.readyState+"ajax错误");
        }
    });

  //后一页
    $(".nextBtn").click(function(){
        event.preventDefault();
        var currentPageCount=parseInt($("#pageFlag").val());
        if($("input:radio[name='radio"+currentPageCount+"']:checked").val()==null){
            alert("请选择！");
            return false;
        }
        var currentPage="page"+currentPageCount;
        var nextPage="page"+(currentPageCount+1);
        if(currentPageCount+1==totalQuestion) {
            $(".divNext").addClass("hidden");
            $(".divComplete").removeClass("hidden");
        }
        else if(currentPageCount==1){
            $(".beforeBtn").removeClass("hidden");

        }
        $("#"+currentPage).addClass("hidden");
        $("#"+nextPage).removeClass("hidden");
        $("#pageFlag").val(currentPageCount+1);
    });
    //前一页
    $(".beforeBtn").click(function(){
        event.preventDefault();
        var currentPageCount=parseInt($("#pageFlag").val());
        var currentPage="page"+currentPageCount;
        var beforePage="page"+(currentPageCount-1);
        $("#"+currentPage).addClass("hidden");
        $("#"+beforePage).removeClass("hidden");
        $("#pageFlag").val(currentPageCount-1);
        if(currentPageCount==totalQuestion) {
            $(".divNext").removeClass("hidden");
            $(".divComplete").addClass("hidden");
        }
        else if(currentPageCount-1==1){
            $(".beforeBtn").addClass("hidden");
        }
    });

    //完成按钮
    $(".completeBtn").click(function(){
        event.preventDefault();
        //获取答案
        var currentPageCount=parseInt($("#pageFlag").val());
        if($("input:radio[name='radio"+currentPageCount+"']:checked").val()==null){
            alert("请选择！");
            return false;
        }
        var arrQuestion_id=[];
        var arrUser_answer=[];
        for(var i=1;i<=totalQuestion;i++){
            arrQuestion_id.push($("#image"+i).data("question-id"));
            arrUser_answer.push($("input[name='radio"+i+"']:checked").val());
        }
        $.ajax({
            url: "/core/saveUserQuestion.action",
            type: "POST",
            data: {question_id:arrQuestion_id.toString(),user_answer:arrUser_answer.toString()},
            success: function (result) {
                window.location.href="/forward/complete.action";
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState);
            }
        });
    });
    function getRootPath_web() {
        //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
        var curWwwPath = window.document.location.href;
        //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
        var pathName = window.document.location.pathname;
        var pos = curWwwPath.indexOf(pathName);
        //获取主机地址，如： http://localhost:8083
        var localhostPaht = curWwwPath.substring(0, pos);
        //获取带"/"的项目名，如：/uimcardprj
        var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
        return (localhostPaht + projectName);
    }
});