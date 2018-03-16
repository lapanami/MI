/**
 * @author an.nd
 */

var config = {
		allow_single_deselect: true,
		disable_search_threshold: 10,
		no_results_text: $("#common\\.pci").html(),
		width: '100%'
	};

$("#sReportCodes").chosen(config);
$("#sReportPeriod").chosen(config);
$("#sReportStatus").chosen(config);
$("#sReportRunSatus").chosen(config);
$("#sReportSource").chosen(config);

$("#btnResetValue").click(function() {
	 $('#sReportCodes').val("");
	 $("#sReportCodes").chosen().trigger("chosen:updated");
	 $('#sReportPeriod').val("");
	 $("#sReportPeriod").chosen().trigger("chosen:updated");
	 $('#sReportStatus').val("");
	 $("#sReportStatus").chosen().trigger("chosen:updated");
	 $('#sReportRunSatus').val("");
	 $("#sReportRunSatus").chosen().trigger("chosen:updated");
	 $('#sReportSource').val("");
	 $("#sReportSource").chosen().trigger("chosen:updated");
	 listAllRegister.ajax.reload(null, false);
}); 


var listAllRegister = $("#listAllRegister").DataTable({
	 "pageLength": 25,
    "lengthMenu": [ 25, 50, 100, 150 ],
    "displayLength": 25,
    "language": {
        "info": $("#common\\.show\\.start\\.end").html(),
        "emptyTable": $("#common\\.emptyTable").html(),
        "zeroRecords": $("#common\\.no\\.record").html(),
        "lengthMenu": $("#common\\.show\\.menu").html(),
        "search": $("#common\\.search").html(),
        "infoEmpty":      $("#common\\.infoEmpty").html(),
        "infoFiltered":   $("#common\\.infoFiltered").html(),
        "paginate": {
            "previous": $("#common\\.previous").html(),
            "next": $("#common\\.next").html(),
            "first": $("#common\\.first").html(),
            "last": $("#common\\.last").html()
          },
          "aria": {
              "sortAscending":  ":" + $("#common\\.sortAscending").html() ,
              "sortDescending": ":" + $("#common\\.sortDescending").html()
          },
          "processing": "<img src='/ReportCenter/static/images/spinner.gif'>"
    },
    "fnRowCallback": function( nRow, aData, iDisplayIndex, iDisplayIndexFull ) {
        $('#listAllRegister tbody tr').css("cursor", "pointer");
        $('#listAllRegister th:first').removeClass('sorting_asc');
//        if ( aData.REPORT_STATUS == "n" )
//        {
//            $('td', nRow).css('background-color', 'Red');
//        }
        
        if(aData.REPORT_STATUS === "n"){
            $(nRow).find('td:eq(6)').css('background-color', '#D70925');
        }
    },
    "rowCallback": function(row, data, index) {
    	
    },
    "sServerMethod": "POST",
    "processing": true,
    "serverSide": true,
    'ajax': {
        'contentType': 'application/json',
        'url': contextPath + "ManagerReportRegister/ViewListReportRegister",
        'type': 'POST',
        'data': function(d) {
        	
        	d.reportCode =$('#sReportCodes').val();
        	d.period =$('#sReportPeriod').val();
        	d.status =$('#sReportStatus').val();
        	d.runStatus =$('#sReportRunSatus').val();
        	d.source =$('#sReportSource').val();
        	
            return JSON.stringify(d);
        }
    },
    "columns": [
    	{ "data": "ID", "name": "ID", "title": "<input name='select_all' value='1' type='checkbox' class='select-checkbox' id='idCheckBoxAll'>" , "width": "2%","className": "dt-center","orderable": false, "render": function ( data, type, row ) {
            return "<input type='checkbox' class='select-checkbox' value='"+data+"'>";
        } },
   	    { "data": "ID", "name": "ID", "title": $("#common\\.rownumber").html()},
        { "data": "REPORT_CODE", "name": "REPORT_CODE", "title": $("#common\\.grid\\.report\\.code").html()},
        { "data": "REPORT_NAME", "name": "REPORT_NAME", "title": $("#common\\.grid\\.report\\.name").html(),"className": "dt-center"},
        { "data": "VALUE", "name": "VALUE", "title": $("#common\\.grid\\.report\\.value").html() ,"className": "dt-center"},
        { "data": "RUN_TIME", "name": "RUN_TIME", "title": $("#common\\.grid\\.report\\.runtime").html(),"className": "dt-center" },
        { "data": "REPORT_STATUS", "name": "REPORT_STATUS", "title": $("#common\\.grid\\.report\\.status").html(),"className": "dt-center", 
        	"render": function ( data, type, row ) {
         		if (row.REPORT_STATUS === 'n') {
         			return 'INACTIVE';
         		}else if(row.REPORT_STATUS === 'a' ){
         			return 'ACTIVE';
         		}
             }
        },
        { "data": "PERIOD", "name": "PERIOD", "title": $("#common\\.grid\\.report\\.period").html() ,"className": "dt-center",
        	"render": function ( data, type, row ) {
         		if (row.PERIOD.trim() === 'D') {
         			return 'DAILY';
         		}else if(row.PERIOD.trim() === 'W' ){
         			return 'WEEK';
         		}else if(row.PERIOD.trim() === 'M' ){
         			return 'MONTH';
         		}else {
         			return row.PERIOD;
         		}
             } 
        },
        { "data": "SOURCE", "name": "SOURCE", "title": $("#common\\.grid\\.report\\.source").html(),"className": "dt-center" },
        { "data": "ID", "name": "ID", "title": ""+$("#common\\.action").html()+"" , "width": "2%","className": "dt-center","orderable": false, "render": function ( data, type, row ) {
        	return "<div style='float:right'>" +
	        			"<table>" +
		        			  "<tr>" +
		        			  "<td style ='padding-right: 5px !important;'>" + "<button type='button' title='"+$("#common\\.button\\.title\\.view").html()+"' style='width:65px' class='btn btn-danger' id='btnView_"+data+"' onclick='btnViewReportInfor(" + JSON.stringify(row) + ")' ><span class='glyphicon glyphicon-eye-open' aria-hidden='true'></span></button>" +
		        			  "</td> " +
		        			  "<td style ='padding-right: 5px !important;'>" + "<button style='width:65px' title='"+$("#common\\.button\\.title\\.edit").html()+"' type='button' class='btn btn-danger btn-order' id='btnEdit_"+data+"' onclick='btnEditReport(" + JSON.stringify(row) +")'><span class='glyphicon glyphicon-pencil' aria-hidden='true'></span></button>" +
					         "</td>" +
					         "<td style ='padding-right: 5px !important;'>" + "<button style='width:65px' title='"+$("#common\\.button\\.title\\.edit").html()+"' type='button' class='btn btn-danger btn-order' id='btnEdit_"+data+"' onclick='btnActiveReport(" + JSON.stringify(row) +")'><span class='glyphicon glyphicon-ok' aria-hidden='true'></span></button>" +
					         "</td>" +
					         "</tr>" +
				         "</table>" +
			        "</div>";
        } }
    ],
    fnServerParams: function(data) {
        data['order'].forEach(function(items, index) {
            data['order'][index]['column'] = data['columns'][items.column]['data'];
        });
    }
});


$("#btnLoadDataExport").click(function() {
	listAllRegister.ajax.reload(null, false);	
});

function btnViewReportInfor(row){
	
}

function btnEditReport(row){
	
}

$("#btnPopCreateRegister").click(function(){
	$("#pUAddRegister").modal("toggle");
});

