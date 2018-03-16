/**
 * @author an.nd
 */


$('#datePicker')
.datepicker({
    format: 'dd/mm/yyyy'
})
.on('changeDate', function(e) {
    // Revalidate the date field
   //$('#eventForm').formValidation('revalidateField', 'date');
});

var config = {
		allow_single_deselect: true,
		disable_search_threshold: 10,
		no_results_text: $("#common\\.pci").html(),
		width: '100%'
	};

$("#sReportCodes").chosen(config);

$("#sReportPeriod").chosen(config);

$("#sReportStatus").chosen(config);

$("#sReportRunStatus").chosen(config);

$("#sReportSource").chosen(config);

$("#btnResetValue").click(function() {
	 $('#sReportCodes').val("");
	 $("#sReportCodes").chosen().trigger("chosen:updated");
	 
	 $('#sReportPeriod').val("");
	 $("#sReportPeriod").chosen().trigger("chosen:updated");
	 
	 $('#sReportStatus').val("");
	 $("#sReportStatus").chosen().trigger("chosen:updated");
	 
	 $('#sReportRunStatus').val("");
	 $("#sReportRunStatus").chosen().trigger("chosen:updated");
	 
	 $('#sReportSource').val("");
	 $("#sReportSource").chosen().trigger("chosen:updated");
	 
	 listAllHistory.ajax.reload(null, false);
}); 

var listAllHistory = $("#listAllHistory").DataTable({
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
       $('#listAllHistory tbody tr').css("cursor", "pointer");
       $('#listAllHistory th:first').removeClass('sorting_asc');
       if(aData.RUN_STATUS.trim() === "FAILED"){
           $(nRow).find('td:eq(8)').css('background-color', '#D70925');
           $(nRow).find('td:eq(8)').css('color', 'white');
       }else if(aData.RUN_STATUS.trim() === "RUNNING"){
    	   $(nRow).find('td:eq(8)').css('background-color', '#FFFF00');
       }
   },
   "rowCallback": function(row, data, index) {
   	
   },
   "sServerMethod": "POST",
   "processing": true,
   "serverSide": true,
   'ajax': {
       'contentType': 'application/json',
       'url': contextPath + "ManagerReportHistory/ViewListHistoryRunReportToday",
       'type': 'POST',
       'data': function(d) {
       	
       	d.reportCode =$('#sReportCodes').val();
       	d.period =$('#sReportPeriod').val();
       	d.status =$('#sReportStatus').val();
       	d.runStatus =$('#sReportRunStatus').val();
       	d.source =$('#sReportSource').val();
       	
    	var startDate = $('#datePicker').data("datepicker");
     	if (startDate && !$('#txtDatefrom').val()) {
     		startDate.setDate(null);
     	}
     	startDate = startDate.dates.length > 0? startDate.getDate() : '';
     	d.dTime= startDate && startDate.getDate()? startDate.getFullYear() + "-" + (startDate.getMonth() + 1) + "-" + startDate.getDate() : '';
       	
           return JSON.stringify(d);
       }
   },
   "columns": [
   	{ "data": "ID", "name": "ID", "title": "<input name='select_all' value='1' type='checkbox' class='select-checkbox' id='idCheckBoxAll'>" , "width": "2%","className": "dt-center","orderable": false, "render": function ( data, type, row ) {
           return "<input type='checkbox' class='select-checkbox' value='"+data+"'>";
       } },
  	    { "data": "ID", "name": "ID", "title": "ID"},
       { "data": "REPORT_CODE", "name": "REPORT_CODE", "title": "REPORT_CODE"},
       { "data": "REPORT_NAME", "name": "REPORT_NAME", "title": "REPORT_NAME","className": "dt-center"},
       { "data": "VALUE", "name": "VALUE", "title": "VALUE" ,"className": "dt-center"},
       { "data": "RUN_TIME", "name": "RUN_TIME", "title": "RUN_TIME","className": "dt-center" },
       { "data": "TDAY", "name": "TDAY", "title":"TDAY","className": "dt-center" },
       { "data": "REPORT_STATUS", "name": "REPORT_STATUS", "title": "REPORT_STATUS","className": "dt-center", 
       	"render": function ( data, type, row ) {
        		if (row.REPORT_STATUS === 'n') {
        			return 'INACTIVE';
        		}else if(row.REPORT_STATUS === 'a' ){
        			return 'ACTIVE';
        		}
            }
       },
       { "data": "RUN_STATUS", "name": "RUN_STATUS", "title":"RUN_STATUS","className": "dt-center" },
       { "data": "PERIOD", "name": "PERIOD", "title": "PERIOD","className": "dt-center",
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
       { "data": "START_RUN_DATE", "name": "START_RUN_DATE", "title":"START_RUN_DATE","className": "dt-center" /*,
           "render": function ( data, type, row ) {
          	 if(data == null)
          		 return null;
               return (new Date(data)).toLocaleDateString("vi-vn");
        } */},
       { "data": "LAST_RUN_DATE", "name": "LAST_RUN_DATE", "title":"LAST_RUN_DATE","className": "dt-center" /*,
           "render": function ( data, type, row ) {
          	 if(data == null)
          		 return null;
               return (new Date(data)).toLocaleDateString("vi-vn");
        } */},
       { "data": "SOURCE", "name": "SOURCE", "title": "SOURCE","className": "dt-center" }
   ],
   fnServerParams: function(data) {
       data['order'].forEach(function(items, index) {
           data['order'][index]['column'] = data['columns'][items.column]['data'];
       });
   }
});
$(document).ready( function () {
setInterval (function test() {
	listAllHistory.ajax.reload(null, false);
	}, 10000);
});


$("#btnLoadDataExport").click(function() {
	listAllHistory.ajax.reload(null, false);	
});