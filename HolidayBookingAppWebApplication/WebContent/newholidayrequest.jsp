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

		<script src="js/holidayContraints.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css">

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
		</script>

		<style>
		* {
		  box-sizing: border-box;
		}
		
		/* Create two equal columns that floats next to each other */
		.column {
		  float: left;
		  width: 40%;
		  padding: 10px;
		}
		
		/* Clear floats after the columns */
		.row:after {
		  content: "";
		  display: table;
		  clear: both;
		}
		</style>
	</head>

	<body>
		<%@ include file="menu.jsp" %>

		<%
			if (isUserLogged != null) {
		%>
			<div class="row">
			  <div class="column">
				<p>Make a holiday request.</p>
				<form method="POST" action="NewHolidayRequest">
					<p>Start Date: <input type="text" name="startDate" id="datepicker-start" readOnly></p>
					<p>End Date: <input type="text" name="endDate" id="datepicker-end" readOnly></p>
					<button id="submitHolidayButton" type="submit">Submit</button>
				</form>
			  </div>

			  <div class="column">
				<div class="errorHolidayDaysRemaining"></div>
				<div class="errorHeadOrDeputyHeadAvailable"></div>
				<div class="errorManagerAvailable"></div>
				<div class="errorSeniorMemberAvailable"></div>
				<div class="errorDepartmentSizeAvailable"></div>
			  </div>
			</div>
			<br>
		<%
			} else {
				response.sendRedirect("/login.jsp");
			}
		%>
	</body>
</html>
