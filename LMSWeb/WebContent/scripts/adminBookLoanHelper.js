var pageSize = 13;
var currentPage = 1;
/**
 * functions for author
 * 
 */

function listBookLoans(searchText, pageNo, pageSize) {
	$.ajax({
		method:"POST",
		url:"listBookLoansPage",
		data:{
			searchText: searchText,
			pageNo: pageNo,
			pageSize : pageSize
		}
	}).done(function (data) {
		var dataString = "";
		var source = $("#bookLoanEntryTemplate").html();
		var template = Handlebars.compile(source);
		data = $.parseJSON(data);
		entries = 0;
		currentPage = pageNo;
		$.each(data, function(id, item){
			var html = template (item);
			dataString += html;
			entries++;
		});
		$("#bookLoanTbody").html(dataString);
	});
	$.ajax({
		method:"POST",
		url:"countBookLoan",
		data:{searchText: searchText}
	}).done(function (data) {
		var noPage = Math.ceil(data/pageSize);
		var dataString = "";
		var source = $("#pageEntryTemplate").html();
		var template = Handlebars.compile(source);
		for(i = 1; i <= noPage; i++){
			var cass = "";
			if (i == pageNo)
				cass = "active";
			var pg = {pageNo : i, pageSize : pageSize, cass : cass, method : 'listBookLoans'};
			var html = template(pg);
			dataString += html;
		}
		$("#pages").html(dataString);
	});
}

function updateBookLoan () {
	$.ajax({
		  method: "POST",
		  url: "updateBookLoan",
		  data: {bookId: $("#edBlBkId").val(),
			  cardNo: $("#edBlCdNo").val(),
			  branchId: $("#edBlBhId").val(),
			  dateOut: $("#edBlDout").val(),
			  dueDate: $("#edBlDdate").val(),}
	}).done(function( msg ) {
		if (!msg) 
			showSuccess("Update BookLoan Due Date Success");
		else
			showWarning(msg);
		listBookLoans($('#searchText').val(), currentPage, pageSize);
	});
}

/**
 * functions run when load
 */

$(function(){
	listBookLoans($('#searchText').val(), 1, pageSize);
});