/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    $.ajax({
        url: "/wechat/JSSDK_Config.action",
        type: "POST",
        data: {total: totalQuestion},
        success: function (result) {
            alert(result.access_token);
            /*
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
                    'showMenuItems',
                    'hideAllNonBaseMenuItem',
                    'showAllNonBaseMenuItem',
                    'translateVoice',
                    'startRecord',
                    'stopRecord',
                    'onRecordEnd',
                    'playVoice',
                    'pauseVoice',
                    'stopVoice',
                    'uploadVoice',
                    'downloadVoice',
                    'chooseImage',
                    'previewImage',
                    'uploadImage',
                    'downloadImage',
                    'getNetworkType',
                    'openLocation',
                    'getLocation',
                    'hideOptionMenu',
                    'showOptionMenu',
                    'closeWindow',
                    'scanQRCode',
                    'chooseWXPay',
                    'openProductSpecificView',
                    'addCard',
                    'chooseCard',
                    'openCard'
                ]
            });*/
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
            $(".pages").html("");
            var style="hidden";
            result.forEach(function(e,i){
                if(i==0){
                   style="";
                }
                else{
                    style="hidden";
                }
                var pageHtml='<div class="page '+style+'" id="page'+(i+1)+'">' +
                    '<div class="container-fluid">' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-12 text-center"><image src="files/'+e.imgUrl+'" data-question-id="'+e.id+'" class="img-thumbnail"></image></div>' +
                    '</div>' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="A">'+e.chkA+'</div>' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="B">'+e.chkB+'</div>' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="C">'+e.chkB+'</div>' +
                    '</div>' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="D">'+e.chkD+'</div>' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="E">'+e.chkE+'</div>' +
                    '<div class="col-xs-4"><input type="radio" name="radio'+(i+1)+'" value="F">'+e.chkF+'</div>' +
                    '</div>' +
                    '</div>' +
                    '</div>';
                $(".pages").append(pageHtml);
            })
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.readyState);
        }
    });

  //后一页
    $(".nextBtn").click(function(){
        event.preventDefault();
        var currentPageCount=parseInt($("#pageFlag").val());
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
        $.ajax({
            url: "/core/saveQuestion.action",
            type: "POST",
            data: {total: totalQuestion},
            success: function (result) {
                window.location.href="/forward/complete.action?pageId="+result;
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest.readyState);
            }
        });
    });

});