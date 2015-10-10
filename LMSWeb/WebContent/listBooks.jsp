<%@page import="com.jdbc.lmdo.Publisher"%>
<%@page import="com.jdbc.lmdo.Genre"%>
<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="com.jdbc.lmdo.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="fixedTopNavbar.jsp" %>
<%@ include file="dashboardTemplate.jsp" %>
<%@ include file="template/bookEntryTemplate.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="./scripts/adminHelper.js"></script>
<script>$(function(){listBooks();});
		$('.selectpicker').selectpicker();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
</head>
<body>
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
	<button type="button" class="btn btn-info" data-toggle="modal" onclick="createBookModal();">
          <span class="glyphicon glyphicon-plus-sign"></span> Create New Book
    </button>
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
    </div>
</body>
</html>