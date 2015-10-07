<%@page import="com.jdbc.lmdo.Publisher"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <% int pubId = Integer.parseInt(request.getParameter("publisherId"));
 	Publisher pub = new AdministratorManagementSys().getPublisherById(pubId); 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Publisher</title>
</head>
<body>
<form name="updatePublisherForm" action="updatePublisher" method="post">
  Publisher's New Name:
  <input type="text" name="publisherName" value="<%=pub.getPublisherName()%>"/>
  <br>Publisher's New Address:
  <input type="text" name="publisherAddress" value="<%=pub.getAddress()%>"/>
  <br>Publisher's New Phone Number:
  <input type="text" name="publisherPhone" value="<%=pub.getPhone()%>"/>
  <br><input type="submit" value="Update" />
  <input type="hidden" id="publisherId" name="publisherId" value="<%=pubId%>"/>
</form>
</body>
</html>