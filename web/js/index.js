/**
 * Created by Administrator on 2017/3/28.
 */
$(function(){
    //参数设置
    //总题数
    var totalQuestion=4;
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

  //抽取10道题
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

});