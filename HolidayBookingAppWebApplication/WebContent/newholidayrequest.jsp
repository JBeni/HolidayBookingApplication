<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.HolidayRequestDTO"%>
<%@ page import="entityclasses.HolidayBookingDTO"%>
<%@ page import="entityclasses.EmployeeDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Add Holiday Request</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

		<!-- Cod taken from https://www.tutorialspoint.com/jqueryui/jqueryui_datepicker.htm -->
		<script type="text/javascript">
			$(function() {
			   $("#datepicker-start").datepicker({
			      showOn: "button",
			      dateFormat: 'dd/mm/yy',
			      buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			      buttonImageOnly: true,
			      buttonText: "Select a date"
			   });
			});
			$(function() {
			   $("#datepicker-end").datepicker({
			      showOn: "button",
			      dateFormat: 'dd/mm/yy',
			      buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			      buttonImageOnly: true,
			      buttonText: "Select a date"
			   });
			});

			$(document).ready(function() {
			    $("#submitHolidayButton").click(function(event) {
			    	if ($('#datepicker-start').val().length == 0 || $('#datepicker-end').val().length == 0) {
			    		event.preventDefault();
			    	}
			    });
			});
		</script>
	</head>

	<body>
		<%@ include file="menu.jsp" %>

		<%
			if (isUserLogged != null) {
		%>
			<p>Make a holiday request.</p>
			<form method="POST" action="NewHolidayRequest">
				<p>Start Date: <input type="text" name="startDate" id="datepicker-start" readOnly></p>
				<p>End Date: <input type="text" name="endDate" id="datepicker-end" readOnly></p>
				<button id="submitHolidayButton" type="submit">Submit</button>
			</form>
			<br>
		<%
			} else {
				response.sendRedirect("/login.jsp");
			}
		%>
	</body>
</html>
