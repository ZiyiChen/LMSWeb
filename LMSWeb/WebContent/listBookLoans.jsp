<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ include file="fixedTopNavbar.jsp" %>
<%@ include file="dashboardTemplate.jsp" %>
<%@ include file="template/bookLoanEntryTemplate.jsp" %>
<%@ include file="template/pageEntryTemplate.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Book Loan List</title>
</head>
<body>
      <table class="table table-striped">
        <thead>
          <tr>
            <th>Book Title</th>
            <th>Branch Name</th>
            <th>Borrower Name</th>
            <th>Date Out</th>
            <th>Due Date</th>
            <th>Date In</th>
          </tr>
        </thead>
        <tbody id="bookLoanTbody">

		</tbody>
	</table>
	</div>
	<div align="middle">
        <ul class="pagination" id="pages">
        
        </ul>
    </div>
    </div>
</body>
<script src="./scripts/adminBookLoanHelper.js"></script>
<script src="./scripts/bootstrap-notify.min.js"></script>
</html>