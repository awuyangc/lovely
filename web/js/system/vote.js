/**
 * Created by wuyang on 2017/5/12.
 */
var table;
$(function(){
    $.extend( $.fn.dataTable.defaults, {
        "ordering": false
    } );
    table=$('#example').DataTable({
        "sDom": "<'row'<'col-md-8'<'myBtn' and T>><'col-md-4'f>>Rtr<'row'<'col-md-6'l><'col-md-6'i>><'row'<'col-md-12'p>>",
        "oLanguage": {
            "sProcessing" : "正在加载中......",
            "sLengthMenu" : "每页显示 _MENU_ 条记录",
            "sZeroRecords" : "",
            "sEmptyTable" : "表中无数据存在！",
            "sInfo" : "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录",
            "sInfoEmpty" : "显示0到0条记录",
            "sInfoFiltered" : "数据表中共为 _MAX_ 条记录",
            "sSearch": "查找：",
            "oPaginate" : {
                "sFirst" : "首页",
                "sPrevious" : "上一页",
                "sNext" : "下一页",
                "sLast" : "末页"
            }
        },
        "tableTools": {
            "sSwfPath": "bower_components/datatables/extensions/TableTools/swf/copy_csv_xls_pdf.swf",
            "sRowSelect": "single"//单选
        },
        "ajax": "/core/getVoteListAll.action",
        "deferRender": true,
        "columns": [
            { data: "question_id" },
            { data: "imgUrl" },
            { data: "answerA" },
            { data: "answerB" },
            { data: "answerC" },
            { data: "answerD" },
            { data: "answerE" }
        ],
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ],
        "fnInitComplete": function (oSettings, json) {
            $('<div style="margin-bottom:8px">' +
                '<button id="btnSolve" class="btn btn-primary btn-sm" >解决问题</button>&nbsp;&nbsp;'+
                '&nbsp;</div>').appendTo($('.myBtn'));
            //选中变色
            $('#example tbody').on( 'click', 'tr', function () {
                if ( $(this).hasClass('selected') ) {
                    $(this).removeClass('selected');
                }
                else {
                    table.$('tr.selected').removeClass('selected');
                    $(this).addClass('selected');
                }
            } );
            //按钮事件
            $("#btnSolve").click(function(){
                //解决按钮触发事件
                if(table.row('.selected').length==0)
                {
                    alert("请选中需要查看的题目！");
                    return ;
                }
                var dataRow=table.row('.selected').data();
                var question_id=dataRow["question_id"];
                var imgUrl=dataRow["imgUrl"];
                var answerA=dataRow["answerA"];
                var answerB=dataRow["answerB"];
                var answerC=dataRow["answerC"];
                var answerD=dataRow["answerD"];
                var answerE=dataRow["answerE"];
                $.ajax({
                    async:false,
                    url: "/core/getQuestion.action",
                    type: "POST",
                    data: {question_id: question_id},
                    success: function (result) {
                        var total = answerA + answerB + answerC + answerD + answerE;
                        if (total == 0) {
                            var persentA=0,persentB=0,persentC=0,persentD=0,persentE=0
                        } else {
                        var persentA = (Math.round(answerA / total * 10000) / 100 + "%");
                        var persentB = (Math.round(answerB / total * 10000) / 100 + "%");
                        var persentC = (Math.round(answerC / total * 10000) / 100 + "%");
                        var persentD = (Math.round(answerD / total * 10000) / 100 + "%");
                        var persentE = (Math.round(answerE / total * 10000) / 100 + "%");
                        }
                        var processA='<div class="wy-progress" style="background-color:red;width: '+persentA+'">'+
                            '</div>'+
                            '<div>'+answerA+" 票 ("+persentA+")"+'</div>';
                        var processB='<div class="wy-progress" style="background-color:orange;width: '+persentB+'">'+
                            '</div>'+
                            '<div>'+answerB+" 票 ("+persentB+")"+'</div>';
                        var processC='<div class="wy-progress" style="background-color:yellow;width: '+persentC+'">'+
                            '</div>'+
                            '<div>'+answerC+" 票 ("+persentC+")"+'</div>';
                        var processD='<div class="wy-progress" style="background-color:green;width: '+persentD+'">'+
                            '</div>'+
                            '<div>'+answerD+" 票 ("+persentD+")"+'</div>';
                        var processE='<div class="wy-progress" style="background-color:greenyellow;width: '+persentE+'">'+
                            '</div>'+
                            '<div>'+answerE+" 票 ("+persentE+")"+'</div>';
                        var contentHtml= '<div class="row" style="padding-bottom:20px">' +
                            '<div class="col-xs-12 text-center"><image src="/files/'+result.imgUrl+'" data-question-id="'+result.id+'" class="img-thumbnail"></image></div>'+
                             '</div>'+
                            '<div class="row" style="padding-bottom:20px">'+
                            '<div class="col-xs-2"></div><div class="col-xs-5"><input type="radio" name="radio" value="A">'+result.chkA+'</div>'+
                            '<div class="col-xs-5" style="display:flex">'+processA+'</div>'+
                            '</div>'+
                            '<div class="row" style="padding-bottom:20px">'+
                            '<div class="col-xs-2"></div><div class="col-xs-5"><input type="radio" name="radio" value="B">'+result.chkB+'</div>'+
                            '<div class="col-xs-5" style="display:flex">'+processB+'</div>'+
                            '</div>'+
                            '<div class="row" style="padding-bottom:20px">'+
                            '<div class="col-xs-2"></div><div class="col-xs-5"><input type="radio" name="radio" value="C">'+result.chkC+'</div>'+
                            '<div class="col-xs-5" style="display:flex">'+processC+'</div>'+
                            '</div>'+
                            '<div class="row" style="padding-bottom:20px">'+
                            '<div class="col-xs-2"></div><div class="col-xs-5"><input type="radio" name="radio" value="D">'+result.chkD+'</div>'+
                            '<div class="col-xs-5" style="display:flex">'+processD+'</div>'+
                            '</div>'+
                            '<div class="row" style="padding-bottom:20px">'+
                            '<div class="col-xs-2"></div><div class="col-xs-5"><input type="radio" name="radio" value="E">'+result.chkE+'</div>'+
                            '<div class="col-xs-5" style="display:flex">'+processE+'</div>'+
                            '</div>';
                        $("#radioOtherArea").html(contentHtml);
                        $("#myModal").modal('toggle');
                    }
                });
                $("#btnSaveVoteAnswer").click(function(){
                    var answer=$("input:radio[name='radio']:checked").val();
                    if(answer==null){
                        alert("请选择答案！");
                        return false;
                    }
                    else{
                        $.ajax({
                            url: "/core/updateQuestionFlag.action",
                            type: "POST",
                            data: {question_id: question_id,answer:answer},
                            success: function (result) {
                                if(result!=0){
                                    $('#myModal').modal('hide');
                                    table.ajax.reload();
                                }
                                else{
                                    alert("失败");
                                }
                            }
                        });
                    }

                });
            });

        },
        "fnRowCallback" : function(nRow, aData, iDisplayIndex) {
            var total=aData.answerA+aData.answerB+aData.answerC+aData.answerD+aData.answerE;
            if(total!=0){
            var persentA= (Math.round(aData.answerA / total * 10000) / 100 + "%");
            var persentB= (Math.round(aData.answerB / total * 10000) / 100 + "%");
            var persentC= (Math.round(aData.answerC / total * 10000) / 100 + "%");
            var persentD= (Math.round(aData.answerD / total * 10000) / 100 + "%");
            var persentE= (Math.round(aData.answerE / total * 10000) / 100 + "%");
            }else {
                var persentA="0%";
                var persentB="0%";
                var persentC="0%";
                var persentD="0%";
                var persentE="0%";
            }
            var processA='<div style="display:inline-flex"><div class="wy-progress" style="background-color:red;width: '+persentA+'">'+
                '</div>'+
                '<div>'+aData.answerA+" 票 ("+persentA+")"+'</div></div>';
            var processB='<div style="display:inline-flex"><div class="wy-progress" style="background-color:orange;width: '+persentB+'">'+
                '</div>'+
                '<div>'+aData.answerB+" 票 ("+persentB+")"+'</div></div>';
            var processC='<div style="display:inline-flex"><div class="wy-progress" style="background-color:yellow;width: '+persentC+'">'+
                '</div>'+
                '<div>'+aData.answerC+" 票 ("+persentC+")"+'</div></div>';
            var processD='<div style="display:inline-flex"><div class="wy-progress" style="background-color:green;width: '+persentD+'">'+
                '</div>'+
                '<div>'+aData.answerD+" 票 ("+persentD+")"+'</div></div>';
            var processE='<div style="display:inline-flex"><div class="wy-progress" style="background-color:greenyellow;width: '+persentE+'">'+
                '</div>'+
                '<div>'+aData.answerE+" 票 ("+persentE+")"+'</div></div>';
                $('td:eq(1)', nRow).html(processA);
                $('td:eq(2)', nRow).html(processB);
                $('td:eq(3)', nRow).html(processC);
                $('td:eq(4)', nRow).html(processD);
                $('td:eq(5)', nRow).html(processE);
            return nRow;
        }
    });
})