<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <% int authId = Integer.parseInt(request.getParameter("authorId"));
 	Author auth = new AdministratorManagementSys().getAuthorById(authId); 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Author</title>
</head>
<body>
<form name="updateAuthorForm" action="updateAuthor" method="post">
  Author New Name:
  <input type="text" name="authorName" value="<%=auth.getAuthorName()%>"/>
  <br><input type="submit" value="Update" />
  <input type="hidden" id="authorId" name="authorId" value="<%=authId%>"/>
</form>
</body>
</html>