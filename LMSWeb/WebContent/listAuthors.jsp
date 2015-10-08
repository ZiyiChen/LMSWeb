<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<% 	List<Author> authors = new AdministratorManagementSys().getAllAuthors(); 
    String message = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Author List</title>
<script type="text/javascript">
	function deleteAuthor(id) {
		document.getElementById('deleId').value = id;
		document.deleteAuthorForm.submit();
	}
	function editAuthor(id) {
		document.getElementById('editId').value = id;
		document.editAuthorForm.submit();
	}
</script>
</head>
<body>
	<form name="createAuthorForm" action="createAuthor.jsp" method="post">
		<input type="submit" value="Create New Author"/>
	</form>
	

	<%if(message != null) {%>
	<span><font color="green"><%=message%></font></span>
	<% } %>
	<%if(error != null) {%>
	<span><font color="red"><%=error%></font></span>
	<% } %>

	<form name="deleteAuthorForm" action="deleteAuthor" method="post">
		<input type="hidden" id="deleId" name="authorId" />
	</form>
	<form name="editAuthorForm" action="editAuthor.jsp" method="post">
		<input type="hidden" id="editId" name="authorId" />
	</form>
	<table>
		<tr>
			<td><b>Author Name</b></td>
			<td><b>Edit</b></td>
			<td><b>Delete</b></td>
		</tr>
		<%
		if (authors != null) {
			for(Author author:authors) {%>
			<tr>
				<td><%=author.getAuthorName()%></td>
				<td><input type="button" value="Edit" onclick="javascript:editAuthor('<%=author.getAuthorId()%>');"/></td>
				<td><input type="button" value="Delete"
					onclick="javascript:deleteAuthor('<%=author.getAuthorId()%>');" /></td>
			</tr>
		<% }
		}%>


	</table>
</body>
</html>