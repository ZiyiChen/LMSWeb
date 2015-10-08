<%@page import="com.jdbc.lmdo.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<% 	List<Publisher> publishers = new AdministratorManagementSys().getAllPublishers(); 
    String message = (String) request.getAttribute("message");
    String error = (String) request.getAttribute("error");
    %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publisher List</title>
<script type="text/javascript">
	function deletePublisher(id) {
		document.getElementById('deleId').value = id;
		document.deletePublisherForm.submit();
	}
	function editPublisher(id) {
		document.getElementById('editId').value = id;
		document.editPublisherForm.submit();
	}
</script>
</head>
<body>
	<form name="createPublisherForm" action="createPublisher.jsp" method="post">
		<input type="submit" value="Create New Publisher"/>
	</form>
	

	<%if(message != null) {%>
	<span><font color="green"><%=message%></font></span>
	<% } %>
	<%if(error != null) {%>
	<span><font color="red"><%=error%></font></span>
	<% } %>

	<form name="deletePublisherForm" action="deletePublisher" method="post">
		<input type="hidden" id="deleId" name="publisherId" />
	</form>
	<form name="editPublisherForm" action="editPublisher.jsp" method="post">
		<input type="hidden" id="editId" name="publisherId" />
	</form>
	<table>
		<tr>
			<td><b>Publisher Name</b></td>
			<td><b>Publisher Address</b></td>
			<td><b>Publisher Phone</b></td>
			<td><b>Edit</b></td>
			<td><b>Delete</b></td>
		</tr>
		<%
		if(publishers != null) {
			for(Publisher publisher:publishers) {%>
			<tr>
				<td><%=publisher.getPublisherName()%></td>
				<td><%=publisher.getAddress()%></td>
				<td><%=publisher.getPhone()%></td>
				<td><input type="button" value="Edit" onclick="javascript:editPublisher('<%=publisher.getPublisherId()%>');"/></td>
				<td><input type="button" value="Delete"
					onclick="javascript:deletePublisher('<%=publisher.getPublisherId()%>');" /></td>
			</tr>
		<% }
		}%>


	</table>
</body>
</html>