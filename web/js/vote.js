/**
 * Created by wuyang on 2017/5/14.
 */
/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    FastClick.attach(document.body);
    $("#pageFlag").val("1");
    var url= location.href.split('#')[0];
    $.ajax({
        url: "/wechat/JSSDK_Config.action",
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
        async:false,
        url: "/core/getQuestionListForVote.action",
        type: "POST",
        data: {total: totalQuestion},
        success: function (result) {
            var style="hidden";
            //提升dom性能
            $(".pages").css("display","none");
            $(".pages").html("");
            var pageHtml="";
            result.forEach(function(e,i){
                if(totalQuestion>result.length){
                    totalQuestion=result.length;
                    if(totalQuestion==1){
                        $(".beforeBtn").addClass("hidden");
                        $(".divNext").addClass("hidden");
                    }
                }
                if(i==0){
                    style="";
                }
                else{
                    style="hidden";
                }
                pageHtml +='<div class="page '+style+'" id="page'+(i+1)+'">' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-12 text-center"><image src="/files/'+e.imgUrl+'" id="image'+(i+1)+'" data-question-id="'+e.id+'" class="img-thumbnail"></image></div>' +
                    '</div>' +
                    '<div class="container-fluid">' +
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-6"><input type="radio" id="radio'+(i+1)+'A" name="radio'+(i+1)+'" value="A"><label id="label'+(i+1)+'A" for="radio'+(i+1)+'A">'+e.chkA+'</label></div>' +
                    '<div class="col-xs-6"><input type="radio" id="radio'+(i+1)+'B" name="radio'+(i+1)+'" value="B"><label id="label'+(i+1)+'B" for="radio'+(i+1)+'B">'+e.chkB+'</label></div>' +
                    '</div>'+
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-6"><input type="radio" id="radio'+(i+1)+'C" name="radio'+(i+1)+'" value="C"><label id="label'+(i+1)+'C" for="radio'+(i+1)+'C">'+e.chkC+'</label></div>' +
                    '<div class="col-xs-6"><input type="radio" id="radio'+(i+1)+'D" name="radio'+(i+1)+'" value="D"><label id="label'+(i+1)+'D" for="radio'+(i+1)+'D">'+e.chkD+'</label></div>' +
                    '</div>'+
                    '<div class="row" style="padding-bottom:20px">' +
                    '<div class="col-xs-6"><input type="radio" id="radio'+(i+1)+'E" name="radio'+(i+1)+'" value="E"><label id="label'+(i+1)+'E" for="radio'+(i+1)+'E">'+e.chkE+'</label></div>' +
                    '</div>' +
                    '<div class="row" style="">' +
                    '<div class="navbar-fixed-bottom text-center">第 '+(i+1)+' 页，共 '+totalQuestion+' 页</div>'+
                    '</div>'+
                    '</div>' +
                    '</div>';
            })
            if(result.length!=0){
                $(".pages").append(pageHtml);
                $(".pages").css("display","");
            }
            else{
                $("#footContent").html("<div class='text-center'><h2>没有可投票项目</h2></div>");
            }

        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.readyState+"ajax错误");
        }
    });

    //后一页
    $(".nextBtn").click(function(){
        event.preventDefault();
        var currentPageCount=parseInt($("#pageFlag").val());

        if($("#progressA"+(currentPageCount+1)).length>0){
            $(".voteBtn").addClass("hidden");
        }else{
            $(".voteBtn").removeClass("hidden");
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
        if($("#progressA"+(currentPageCount-1)).length>0){
            $(".voteBtn").addClass("hidden");
        }else{
            $(".voteBtn").removeClass("hidden");
        }
        var currentPage="page"+currentPageCount;
        var beforePage="page"+(currentPageCount-1);
        $("#"+currentPage).addClass("hidden");
        $("#"+beforePage).removeClass("hidden");
        $("#pageFlag").val(currentPageCount-1);
        if(currentPageCount==totalQuestion) {
            $(".divNext").removeClass("hidden");
        }
        else if(currentPageCount-1==1){
            $(".beforeBtn").addClass("hidden");
        }
    });


    //投票按钮
    $(".voteBtn").click(function(){
        event.preventDefault();
        //获取答案
        var currentPageCount=parseInt($("#pageFlag").val());
        var voteAnswer=$("input:radio[name='radio"+currentPageCount+"']:checked").val();
        if(voteAnswer==null){
            alert("请选择！");
            return false;
        }
        $(".voteBtn").addClass("hidden");
        //选项
        var labelA=$("#label"+currentPageCount+"A").html();
        var labelB=$("#label"+currentPageCount+"B").html();
        var labelC=$("#label"+currentPageCount+"C").html();
        var labelD=$("#label"+currentPageCount+"D").html();
        var labelE=$("#label"+currentPageCount+"E").html();
        var question_id=$("#image"+currentPageCount).data("question-id");
        var dom=$("#page"+currentPageCount+" .container-fluid")
        dom.css("display","none");
        $.ajax({
            url: "/core/getVoteQuestion.action",
            data: {question_id:question_id},
            success: function (vote) {
                var resultA=vote.A;
                var resultB=vote.B;
                var resultC=vote.C;
                var resultD=vote.D;
                var resultE=vote.E;
                //改善性能，采用先显示结果后存储值的方式,理论上有掉票的可能
                if(voteAnswer=="A"){
                    resultA =resultA+1;
                }else if(voteAnswer=="B"){
                    resultB =resultB+1;
                }else if(voteAnswer=="C"){
                    resultC =resultC+1;
                }else if(voteAnswer=="D"){
                    resultD =resultD+1;
                }else if(voteAnswer=="E"){
                    resultE =resultE+1;
                };
                var total=resultA+resultB+resultC+resultD+resultE;
                var persentA= (Math.round(resultA / total * 10000) / 100 + "%");
                var persentB= (Math.round(resultB / total * 10000) / 100 + "%");
                var persentC= (Math.round(resultC / total * 10000) / 100 + "%");
                var persentD= (Math.round(resultD / total * 10000) / 100 + "%");
                var persentE= (Math.round(resultE/ total * 10000) / 100 + "%");
                var voteHtml=$('#voteHtml').clone();
                voteHtml.removeClass("hidden");
                voteHtml.find("#chkA").attr("id","chkA"+currentPageCount).html(labelA);
                voteHtml.find("#progressA").attr("id","progressA"+currentPageCount).css("width",persentA);
                voteHtml.find("#ticketA").attr("id","ticketA"+currentPageCount).html(resultA+"票（"+persentA+"）");
                voteHtml.find("#chkB").attr("id","chkB"+currentPageCount).html(labelB);
                voteHtml.find("#progressB").attr("id","progressB"+currentPageCount).css("width",persentB);
                voteHtml.find("#ticketB").attr("id","ticketB"+currentPageCount).html(resultB+"票（"+persentB+"）");
                voteHtml.find("#chkC").attr("id","chkC"+currentPageCount).html(labelC);
                voteHtml.find("#progressC").attr("id","progressC"+currentPageCount).css("width",persentC);
                voteHtml.find("#ticketC").attr("id","ticketC"+currentPageCount).html(resultC+"票（"+persentC+"）");
                voteHtml.find("#chkD").attr("id","chkD"+currentPageCount).html(labelD);
                voteHtml.find("#progressD").attr("id","progressD"+currentPageCount).css("width",persentD);
                voteHtml.find("#ticketD").attr("id","ticketD"+currentPageCount).html(resultD+"票（"+persentD+"）");
                voteHtml.find("#chkE").attr("id","chkE"+currentPageCount).html(labelE);
                voteHtml.find("#progressE").attr("id","progressE"+currentPageCount).css("width",persentE);
                voteHtml.find("#ticketE").attr("id","ticketE"+currentPageCount).html(resultE+"票（"+persentE+"）");
                voteHtml.attr("id","voteHtml"+currentPageCount);
                dom.html(voteHtml);
                dom.css("display","");
                $.ajax({
                    async:false,
                    url: "/core/saveVoteQuestion.action",
                    data: {question_id:question_id,voteAnswer:voteAnswer},
                    success: function (result) {
                        //获取当前题目的所有投票结果
                    }
                });
            }
        });

    });

});