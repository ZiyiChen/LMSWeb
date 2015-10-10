/**
 * 
 */

function listBooks() {
	var dataString = "";
	var source = $("#bookEntryTemplate").html();
	var template = Handlebars.compile(source);
	$.ajax({
		method:"POST",
		url:"listBooks"
	}).done(function (data) {
		data = $.parseJSON(data);
		$.each(data, function(id, item){
			var html = template (item);
			dataString += html;
		});
		$("#bookTbody").html(dataString);
	});
}

function addBook () {
	$.ajax({
		  method: "POST",
		  url: "addBook",
		  data: { bookTitle: $("#crtBkTitle").val(),
			  bookPublisher: $("#crtBkPubSel").val(),
			  addedAuthors: $("#crtBkAuthSel").val(),
			  addedGenres: $("#crtBkGenSel").val()}
	}).done(function( msg ) {
		$("#message").html(msg);
		listBooks();
	});
}

var loadOnce = false;
function createBookModal() {
	if(!loadOnce) {
		getValidPublisher(null, $('#crtBkPubSel'));
		getValidAuthor(null, $('#crtBkAuthSel'));
		getValidGenre(null, $('#crtBkGenSel'));
		loadOnce = true;
	} else 
		resetDataWithin($('#createBookModal'));
	$('#createBookModal').modal();
}

function resetDataWithin (element) {
	element.find("input,textarea,select").val('');
	$('.selectpicker').selectpicker('refresh');
}

function getValidPublisher (bkId, selectionElement) {
	$.ajax({
		method:"POST",
		url:"listPublishers"
	}).done(function (data) {
		var pubArr = $.parseJSON(data);
		if (bkId) {
			var bk;
			$.ajax({
				method:"POST",
				url:"getBookById",
				data: { bookId: bkId}
			}).done(function (data) {
				bk = $.parseJSON(data);
			});
			if (!bk.publisher) {
				bk.publisher = [];
			}
			$.each(pubArr, function(index, item) {
				var isSelected = (item.publisherId == bk.publisher.publisherId);
				selectionElement.append($('<option>', { 
			        value: item.publisherId,
			        text : item.publisherName,
			        selected : isSelected
			    }));
				selectionElement.selectpicker('refresh');
			});
		}else {
			$.each(pubArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.publisherId,
			        text : item.publisherName
			    }));
				selectionElement.selectpicker('refresh');
			});
		}
	});
}

function getValidAuthor (bkId, selectionElement) {
	$.ajax({
		method:"POST",
		url:"listAuthors"
	}).done(function (data) {
		var authArr = $.parseJSON(data);
		if (bkId) {
			var bk;
			$.ajax({
				method:"POST",
				url:"getBookById",
				data: { bookId: bkId}
			}).done(function (data) {
				bk = $.parseJSON(data);
			});
			if (!bk.authors) {
				bk.authors = [];
			}
			$.each(authArr, function(index, item) {
				var isSelected = (jQuery.inArray(item, bk.authors) !== -1);
				selectionElement.append($('<option>', { 
			        value: item.authorId,
			        text : item.authorName,
			        selected : isSelected
			    }));
				selectionElement.selectpicker('refresh');
			});
		}else {
			$.each(authArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.authorId,
			        text : item.authorName
			    }));
				selectionElement.selectpicker('refresh');
			});
		}
	});
}

function getValidGenre (bkId, selectionElement) {
	$.ajax({
		method:"POST",
		url:"listGenres"
	}).done(function (data) {
		var genArr = $.parseJSON(data);
		if (bkId) {
			var bk;
			$.ajax({
				method:"POST",
				url:"getBookById",
				data: { bookId: bkId}
			}).done(function (data) {
				bk = $.parseJSON(data);
			});
			if (!bk.genres) {
				bk.genres = [];
			}
			$.each(genArr, function(index, item) {
				var isSelected = (jQuery.inArray(item, bk.genres) !== -1);
				selectionElement.append($('<option>', { 
			        value: item.genreId,
			        text : item.genreName,
			        selected : isSelected
			    }));
				selectionElement.selectpicker('refresh');
			});
		}else {
			$.each(genArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.genreId,
			        text : item.genreName
			    }));
				selectionElement.selectpicker('refresh');
			});
		}
	});
}

function deleteBook(id) {
	var s = id;
	$.ajax({
		method:"POST",
		url:"deleteBook",
		data: { bookId: id}
	}).done(function (data) {
		$("#message").html(data);
		listBooks();
	});
}
function editBook(id) {
	document.getElementById('editId').value = id;
	document.editBookForm.submit();
}