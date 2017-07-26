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
        "ajax": "/core/getQuestionListAll.action",
        "deferRender": true,
        "columns": [
            { data:"id" },
            { data:"imgUrl"},
            { data: "chkA" },
            { data: "chkB" },
            { data: "chkC" },
            { data: "chkD" },
            { data: "chkE" },
            { data: "chkF" },
            { data: "answer" },
            { data: "isDelete" },
            {data: "createtime" },
            {data: "flag" }
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
                '<a href="#" id="delete" class="btn btn-danger btn-sm" >删除</a>&nbsp;&nbsp;'+
                '<a href="#" id="edit" class="btn btn-primary btn-sm" >恢复</a> '+
                '<a href="#" id="edit" class="btn btn-primary btn-sm" >变成待解决</a> '+'&nbsp;</div>').appendTo($('.myBtn'));

        }
    });
    $('#example tbody').on( 'click', 'tr', function () {
        if ( $(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }
        else {
            table.$('tr.selected').removeClass('selected');
            $(this).addClass('selected');
        }
    } );
})