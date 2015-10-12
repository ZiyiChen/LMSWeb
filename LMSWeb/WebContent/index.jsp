<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="fixedTopNavbar.jsp" %>
<%@ include file="dashboardTemplate.jsp" %>
	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        	<h1 class="page-header">Home</h1>
	
			<div class="container">
		
		      <!-- Main component for a primary marketing message or call to action -->
		      <div class="jumbotron">
		        <h3>Welcome to Library Management System</h3>
		      </div>
		    </div>
		    <div class="well">
		    <button id="dp4"> <span class="glyphicon glyphicon-plus-sign"></span></button>
				<input type="text" class="form-control" id="dp3" value=""> 
          </div>
   	</div>
   	<script>
		$(function(){
			$('#dp4').datepicker();   
			$('#dp3').datepicker({language: 'en',
			    format: 'yyyy-mm-dd',
			    startDate: '2015-10-20'
			    });
			$('#dp3').datepicker('setDate', new Date(2015, 10, 23));
		});
		
	</script>
    <link href="./Bootstrap/datepicker.css" rel="stylesheet">
    <script src="./Bootstrap/prettify.js"></script><style type="text/css"></style>
    <script src="./Bootstrap/bootstrap-datepicker.js"></script>
    
   	
   	