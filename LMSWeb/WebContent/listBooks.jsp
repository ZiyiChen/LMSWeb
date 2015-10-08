<%@page import="com.jdbc.lmdo.Genre"%>
<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="com.jdbc.lmdo.Book"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="fixedTopNavbar.jsp" %>
<%@ include file="dashboardTemplate.jsp" %>
<% 	List<Book> books = new AdministratorManagementSys().getAllFullLoadBooks(); 
    String message = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book List</title>
<script type="text/javascript">
	function deleteBook(id) {
		document.getElementById('deleId').value = id;
		document.deleteBookForm.submit();
	}
	function editBook(id) {
		document.getElementById('editId').value = id;
		document.editBookForm.submit();
	}
</script>
</head>
<body>
	<form name="createBookForm" action="createBook.jsp" method="post">
		<input type="submit" value="Create New Book"/>
	</form>
	
	
	<%if(message != null) {%>
	<span><font color="green"><%=message%></font></span>
	<% } %>
	<%if(error != null) {%>
	<span><font color="red"><%=error%></font></span>
	<% } %>

	<form name="deleteBookForm" action="deleteBook" method="post">
		<input type="hidden" id="deleId" name="bookId" />
	</form>
	<form name="editBookForm" action="editBook.jsp" method="post">
		<input type="hidden" id="editId" name="bookId" />
	</form>
	<div class="table-responsive">
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
              <tbody>

		<%
		if (books != null) {
			for(Book book:books) {%>
			<tr>
				<td><%=book.getTitle()%></td>
				<td>
				<%if(book.getPublisher() != null) { %>
				<%=book.getPublisher().getPublisherName()%>
				<%} %></td>
				<td><%
				List<Author> auths = book.getAuthors();
				if (auths != null) {
					for(Author a : auths) {%>
						<%=a.getAuthorName()%>,&nbsp;
						<%}
					}%>
				</td>
				<td><%
				List<Genre> genres = book.getGenres();
				if (genres != null) {
				for(Genre g : genres) {%>
					<%=g.getGenreName()%>,&nbsp;
					<%}
				}%>
				</td>
				<td><input type="button" value="Edit" onclick="javascript:editBook('<%=book.getBookId()%>');"/></td>
				<td><input type="button" value="Delete"
					onclick="javascript:deleteBook('<%=book.getBookId()%>');" /></td>
			</tr>
		<% }
		}%>


	</table>
	</div>
</body>
</html>