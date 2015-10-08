<%@page import="com.jdbc.lmdo.Genre"%>
<%@page import="com.jdbc.lmdo.Author"%>
<%@page import="com.jdbc.lmdo.Publisher"%>
<%@page import="java.util.List"%>
<%@page import="com.jdbc.lmdo.Book"%>
<%@page import="com.jdbc.lmdo.Book"%>
<%@page import="com.jdbc.lmsys.AdministratorManagementSys"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <% int bkId = Integer.parseInt(request.getParameter("bookId"));
 	Book bk = new AdministratorManagementSys().getFullLoadBookById(bkId); 
    int pubId = bk.getPublisher()==null?-1:bk.getPublisher().getPublisherId();
 	AdministratorManagementSys amSys = new AdministratorManagementSys(); 
	List<Publisher> pubs = amSys.getAllPublishers();
   	List<Author> auths = amSys.getAllValidAuthorsByBook(bk);
   	List<Genre> genres = amSys.getAllValidGenresByBook(bk);
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
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
	 seleAuth = document.updateBookForm.updatedAuthors;
     for (i = 0; i < seleAuth.options.length; i++) { 
    	 seleAuth.options[i].selected = true; 
     } 
     seleGen = document.updateBookForm.updatedGenres;
     for (i = 0; i < seleGen.options.length; i++) { 
    	 seleGen.options[i].selected = true; 
     }
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Book</title>
</head>
<body>
	<form name="updateBookForm" action="updateBook" method="post">
		Book New Title: <input type="text" name="bookTitle" value="<%=bk.getTitle()!=null?bk.getTitle():"" %>"/> 
		
		<br><br>Book New Publisher: <br><select name="bookPublisher" style="width:300px" >
		<option value=-1>...</option>
		<%
		if (pubs != null) {
			for(Publisher p : pubs) {%>
			<option value="<%=p.getPublisherId()%>"
			<%if (p.getPublisherId() == pubId) { %>
				selected="selected"
			<%} %>><%=p.getPublisherName()%></option>
			<% } 
		}%>
		</select> 
		
		<br><br>Book New Authors: 
		<br><select name="allAuthors" style="width:300px" multiple>
            <%
			if (auths != null) {
				for(Author a : auths) {%>
				<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName() %></option>
				<% } 
			}%>
        </select>
        <input type="button" style="width:100px" value="<< Remove" onClick="javascript:moveRows(document.updateBookForm.updatedAuthors,document.updateBookForm.allAuthors)">
        <input type="button" style="width:100px" value="Add >>" onClick="javascript:moveRows(document.updateBookForm.allAuthors,document.updateBookForm.updatedAuthors)">
		<select name="updatedAuthors" style="width:300px" multiple>
			<%
			if (auths != null) {
				for(Author a : bk.getAuthors()) {%>
				<option value="<%=a.getAuthorId()%>"><%=a.getAuthorName() %></option>
				<% } 
			}%>
        </select>
        
		<br><br>Book New Genres: 
		<br><select name="allGenres" style="width:300px" multiple>
            <%
			if (genres != null) {
				for(Genre g : genres) {%>
				<option value="<%=g.getGenreId()%>"><%=g.getGenreName() %></option>
				<% } 
			}%>
        </select>
        <input type="button" style="width:100px" value="<< Remove" onClick="javascript:moveRows(document.updateBookForm.updatedGenres,document.updateBookForm.allGenres)">
        <input type="button" style="width:100px" value="Add >>" onClick="javascript:moveRows(document.updateBookForm.allGenres,document.updateBookForm.updatedGenres)">
		<select name="updatedGenres" style="width:300px" multiple>
			 <%
			if (genres != null) {
				for(Genre g : bk.getGenres()) {%>
				<option value="<%=g.getGenreId()%>"><%=g.getGenreName() %></option>
				<% } 
			}%>
        </select>
        <input type="hidden" id="bookId" name="bookId" value="<%=bkId%>"/>
		<br><br><input type="submit" style="width:100px" value="Update" onclick="javascript:selectAllAddedAuthorsGenres();" />
	</form>
</body>
</html>