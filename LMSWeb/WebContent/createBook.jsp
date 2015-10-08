<%@page import="com.jdbc.lmdo.Publisher"%>
<%@page import="com.jdbc.lmdo.Genre"%>
<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<% 	AdministratorManagementSys amSys = new AdministratorManagementSys(); 
	List<Publisher> pubs = amSys.getAllPublishers();
   	List<Author> auths = amSys.getAllAuthors();
   	List<Genre> genres = amSys.getAllGenres();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
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
function selectAllAddedAuthorsGenres() {
	 seleAuth = document.addBookForm.addedAuthors;
     for (i = 0; i < seleAuth.options.length; i++) { 
    	 seleAuth.options[i].selected = true; 
     } 
     seleGen = document.addBookForm.addedGenres;
     for (i = 0; i < seleGen.options.length; i++) { 
    	 seleGen.options[i].selected = true; 
     }
}
</script>

	<form name="addBookForm" action="addBook" method="post">
		New Book Title: <input type="text" name="bookTitle" /> 
		
		<br><br>New Book Publisher: <br><select name="bookPublisher" style="width:300px">
		<option value=-1 selected="selected">...</option>
		<%
		if (pubs != null) {
			for(Publisher p : pubs) {%>
			<option value="<%=p.getPublisherId()%>"><%=p.getPublisherName()%></option>
			<% } 
		}%>
		</select> 
		
		<br><br>New Book Authors: 
		<br><select name="allAuthors" style="width:300px" multiple>
            <%
			if (auths != null) {
				for(Author a : auths) {%>
				<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName() %></option>
				<% } 
			}%>
        </select>
        <input type="button" style="width:100px" value="<< Remove" onClick="javascript:moveRows(document.addBookForm.addedAuthors,document.addBookForm.allAuthors)">
        <input type="button" style="width:100px" value="Add >>" onClick="javascript:moveRows(document.addBookForm.allAuthors,document.addBookForm.addedAuthors)">
		<select name="addedAuthors" style="width:300px" multiple>
        </select>
        
		<br><br>New Book Genres: 
		<br><select name="allGenres" style="width:300px" multiple>
            <%
			if (genres != null) {
				for(Genre g : genres) {%>
				<option value="<%=g.getGenreId()%>"><%=g.getGenreName() %></option>
				<% } 
			}%>
        </select>
        <input type="button" style="width:100px" value="<< Remove" onClick="javascript:moveRows(document.addBookForm.addedGenres,document.addBookForm.allGenres)">
        <input type="button" style="width:100px" value="Add >>" onClick="javascript:moveRows(document.addBookForm.allGenres,document.addBookForm.addedGenres)">
		<select name="addedGenres" style="width:300px" multiple>
        </select>
        
		<br><br><input type="submit" style="width:100px" value="Add" onclick="javascript:selectAllAddedAuthorsGenres();" />
	</form>
</body>
</html>