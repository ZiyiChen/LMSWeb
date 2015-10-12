<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="fixedTopNavbar.jsp" %>
<%@ include file="dashboardTemplate.jsp" %>
<%@ include file="template/bookEntryTemplate.jsp" %>
<%@ include file="template/pageEntryTemplate.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="./Bootstrap/bootstrap-select.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
</head>
<body>

	<div class="modal fade" id="deleteBookModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Delete Book</h4>
				</div>
				<div class="modal-body">
					<div id="delBkMsg"> Are you sure you want to delete this book?</div>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="deBkId">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-danger" onclick="javascript:destoryBook();" data-dismiss="modal">Yes</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="editBookModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Edit Book</h4>
				</div>
				<div class="modal-body">
					<form name="editBookForm" action="editBook" method="post">
						<input type="hidden" id="edBkId">
						Book Title: <input type="text" name="bookTitle" class="form-control" id="edBkTitle"/> 
						
						<br>Book Publisher: 
						<br><select class="selectpicker" data-width="100%" name="bookPublisher" id="edBkPubSel" data-live-search="true">
						<option value=-1 selected="selected">...</option>
						
						</select> 
						
						<br><br>Book Authors: 
						<br><select name="addedAuthors" data-width="100%" class="selectpicker" id="edBkAuthSel" data-live-search="true" multiple>
				        </select>
				        
						<br><br>Book Genres: 
						<br><select name="addedGenres" data-width="100%" class="selectpicker" id="edBkGenSel" data-live-search="true" multiple>
				        </select>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="javascript:updateBook();" data-dismiss="modal">Update</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<div class="modal fade" id="createBookModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">Create Book</h4>
				</div>
				<div class="modal-body">
					<form name="addBookForm" action="addBook" method="post">
						New Book Title: <input type="text" name="bookTitle" class="form-control" id="crtBkTitle"/> 
						
						<br>New Book Publisher: 
						<br><select class="selectpicker" data-width="100%" name="bookPublisher" id="crtBkPubSel" data-live-search="true">
						<option value=-1 selected="selected">...</option>
						
						</select> 
						
						<br><br>New Book Authors: 
						<br><select name="addedAuthors" data-width="100%" class="selectpicker" id="crtBkAuthSel" data-live-search="true" multiple>
				        </select>
				        
						<br><br>New Book Genres: 
						<br><select name="addedGenres" data-width="100%" class="selectpicker" id="crtBkGenSel" data-live-search="true" multiple>
				        </select>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="javascript:addBook();" data-dismiss="modal">Add</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<div class="table-responsive">
	<div class="col-md-2 col-xs-9 nopadding"><input type="text" class="form-control" placeholder="Enter text to search" id="searchText" onkeyup="javascript:listBooks($('#searchText').val(), 1, pageSize);"></div>
	<div align="right">
	<button type="button" class="btn btn-info" data-toggle="modal" onclick="createBookModal();">
          <span class="glyphicon glyphicon-plus-sign"></span> Create New Book
    </button>
    </div>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Book Title</th>
            <th>Book Publisher</th>
            <th>Book Authors</th>
            <th>Book Genres</th>
            <th>Edit</th>
            <th>Delete</th>
          </tr>
        </thead>
        <tbody id="bookTbody">

		</tbody>
	</table>
	</div>
	<div align="middle">
        <ul class="pagination" id="pages">
        
        </ul>
    </div>
    </div>
</body>
<script src="./scripts/adminBookHelper.js"></script>
<script src="./scripts/bootstrap-notify.min.js"></script>
<script src="./Bootstrap/bootstrap-select.min.js"></script>
</html>