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

function moveRows(ms1,ms2){
	// Move rows from ms1 to ms2
	for (i = ms1.options.length - 1; i >= 0; i--){
		if (ms1.options[i].selected == true){
			var newRow = new Option(ms1.options[i].text,ms1.options[i].value);
			ms2.options[ms2.options.length]=newRow;
			ms1.options[i]=null;
		}
	}
}
function addBook () {
	$.ajax({
		  method: "POST",
		  url: "addBook",
		  data: { bookTitle: $("#bookTitle").val(),
			  bookPublisher: $("#bookPublisher").val(),
			  addedAuthors: $("#addedAuthors").val(),
			  addedGenres: $("#addedGenres").val()}
		}).done(function( msg ) {
		    $("#message").html(msg);
		  });
}

function createBookModal() {
	getValidPublisher(null, $('#crtBkPubSel'));
	getValidPublisher(null, $('#crtBkAuthSel'));
	getValidPublisher(null, $('#crtBkGenSel'));
	$('#createBookModal').modal();
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
			if (bk.publisher) {
				$.each(pubArr, function(index, item) {
					var isSelected = (item.publisherId == bk.publisher.publisherId);
					selectionElement.append($('<option>', { 
				        value: item.publisherId,
				        text : item.publisherName,
				        selected : isSelected
				    }));
				});
			}
		}else {
			$.each(pubArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.publisherId,
			        text : item.publisherName
			    }));
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
			if (bk.authors) {
				$.each(authArr, function(index, item) {
					var isSelected = (jQuery.inArray(item, bk.authors) !== -1);
					selectionElement.append($('<option>', { 
				        value: item.authorId,
				        text : item.authorName,
				        selected : isSelected
				    }));
				});
			}
		}else {
			$.each(authArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.authorId,
			        text : item.authorName
			    }));
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
			if (bk.genres) {
				$.each(genArr, function(index, item) {
					var isSelected = (jQuery.inArray(item, bk.genres) !== -1);
					selectionElement.append($('<option>', { 
				        value: item.genreId,
				        text : item.genreName,
				        selected : isSelected
				    }));
				});
			}
		}else {
			$.each(genArr, function(index, item) {
				selectionElement.append($('<option>', { 
			        value: item.genreId,
			        text : item.genreName
			    }));
			});
		}
	});
}

function deleteBook(id) {
	document.getElementById('deleId').value = id;
	document.deleteBookForm.submit();
}
function editBook(id) {
	document.getElementById('editId').value = id;
	document.editBookForm.submit();
}