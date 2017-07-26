/**
 * Created by Administrator on 2017/5/9.
 */
$(function(){
    FastClick.attach(document.body);
    var errorQuestion="";
    var errorAnswer="";
    var rightAnswer="";
    var paper_id="";
    $.ajax({
        async:false,
        url: "/core/resultScore.action",
        type: "POST",
        data: {param:"结果展示"},
        success: function (result) {
                paper_id=result.id;
                errorQuestion=result.error_question;
                errorAnswer=result.error_answer;
                rightAnswer=result.right_answer;
                $("#scoreArea").html("您一共答对 "+result.score+" 题");
        }
    });

    $("#btnDetail").click(function(){
        $.ajax({
            async:false,
            url: "/core/resultDetail.action",
            type: "POST",
            data: {question_id:errorQuestion},
            success: function (result) {
                //先隐藏提升dom性能
                $("#detailArea").css("display","none");
                $("#detailArea").html("");
                $("#detailArea").append("<div class='container-fluid'><div class='col-xs-2' style='background-color: green;height:20px;border-radius: 5px'></div>&nbsp;标准答案</div></div>");
                result.forEach(function(e,i){
                    var userAnswer=errorAnswer.split(",")[i].toString();
                    var standRightAnswer=rightAnswer.split(",")[i].toString();
                    var pageHtml='<div class="container-fluid text-center" style="padding-top:20px">' +
                        '<div style="border-top:dotted"></div>'+
                        '<div class="row" style="padding-top:20px">' +
                        '<div class="col-xs-12 text-center"><image src="/files/'+e.imgUrl+'" id="image'+(i+1)+'" data-question-id="'+e.id+'" class="img-thumbnail"></image></div>' +
                        '</div>' +
                        '<div class="row" style="padding-bottom:20px">' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="A"><span style="padding:5px;border-radius: 5px">'+e.chkA+'</span></div>' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="B"><span style="padding:5px;border-radius: 5px">'+e.chkB+'</span></div>' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="C"><span style="padding:5px;border-radius: 5px">'+e.chkC+'</span></div>' +
                        '</div>' +
                        '<div class="row" style="padding-bottom:20px">' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="D"><span style="padding:5px;border-radius: 5px">'+e.chkD+'</span></div>' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="E"><span style="padding:5px;border-radius: 5px">'+e.chkE+'</span></div>' +
                        '<div class="col-xs-4"><input disabled="disabled" type="radio" name="radio'+(i+1)+'" value="F"><span style="padding:5px;border-radius: 5px">'+e.chkF+'</span></div>' +
                        '</div>' +
                        '<div class="row">' +
                        '<div class="col-xs-12 text-right">'+
                        '<a class="otherAnswer" data-id="'+e.id+'" data-rightanswer="'+standRightAnswer+'" data-a="'+e.chkA+'" data-b="'+e.chkB+'" data-c="'+e.chkC+'" data-d="'+e.chkD+'" data-e="'+e.chkE+'" data-f="'+e.chkF+'" href="#">有异议？</a>' +
                        '</div>'+
                        '</div>'+
                        '</div>';
                    $("#detailArea").append(pageHtml);
                    //用户选中的答案
                    $("input:radio[name='radio"+(i+1)+"'][value='"+userAnswer+"']").attr("checked",true);
                    //$("input:radio[name='radio"+(i+1)+"'][value='"+userAnswer+"']").parent().css("background-color","red")
                    //标准答案
                    $("input:radio[name='radio"+(i+1)+"'][value='"+standRightAnswer+"']").next().css("background-color","green")
                });
                $("#detailArea").css("display","");
            }
        });
        $(".otherAnswer").click(function(){
            event.preventDefault();
            var question_id=$(this).data("id");
            var question_rightAnswer=$(this).data("rightanswer");
            var question_A=$(this).data("a");
            var question_B=$(this).data("b");
            var question_C=$(this).data("c");
            var question_D=$(this).data("d");
            var question_E=$(this).data("e");
            var bodyHtml='<div class="container-fluid text-center" id="otherAnswer_div" data-question-id="'+question_id+'" style="padding-top:20px">'+
                '<div class="row" style="padding-bottom:20px">'+
                '<div class="col-xs-4"><input type="radio" name="radioOther" value="A">'+question_A+'</div>'+
                '<div class="col-xs-4"><input type="radio" name="radioOther" value="B">'+question_B+'</div>'+
                '<div class="col-xs-4"><input type="radio" name="radioOther" value="C">'+question_C+'</div>'+
                '</div>'+
                '<div class="row" style="padding-bottom:20px">'+
                '<div class="col-xs-4"><input type="radio" name="radioOther" value="D">'+question_D+'</div>'+
                '<div class="col-xs-4"><input type="radio" name="radioOther" value="E">'+question_E+'</div>'+
                '</div>'+
                '<div class="row" style="padding-bottom:20px">'+
                '<div class="col-xs-2 text-center" style="padding-right:0px;padding-left:0px">理由：</div>'+
                '<div class="col-xs-10 text-left"><textarea id="textareaReason" style="width:100%" rows="3"></textarea></div>'+
                '</div>'+
                '</div>';
            $("#radioOtherArea").html(bodyHtml);
            //$("input:radio[name='radioOther'][value='"+question_rightAnswer+"']").attr("disabled","disabled");
            $("input:radio[name='radioOther'][value='"+question_rightAnswer+"']").parent().html("");
            $("#myModal").modal('toggle');
        });

        $("#btnSaveOtherAnswer").click(function(){
           //判断是否选中选项并填写理由
            if($("input:radio[name='radioOther']:checked").val()==null){
                alert("请选中！");
                return false;
            }
            if($.trim($("#textareaReason").val())==""){
                alert("请填写理由！");
                return false;
            }
            var otherAnswer=$("input[name='radioOther']:checked").val();
            var question_id=$("#otherAnswer_div").data("question-id");
            var reason=$("#textareaReason").val();
            $.ajax({
                async:false,
                url: "/core/saveOtherAnswer.action",
                type: "POST",
                data: {paper_id:paper_id,question_id:question_id,otherAnswer:otherAnswer,reason:reason},
                success: function (result) {
                    alert("谢谢您的反馈！");
                    $('#myModal').modal('hide');
                }
            });
        });
    });
});