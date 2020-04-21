<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.HolidayRequestDTO"%>
<%@ page import="entityclasses.HolidayBookingDTO"%>
<%@ page import="entityclasses.EmployeeDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Employee Working/On leave</title>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

		<!-- Cod taken from https://www.tutorialspoint.com/jqueryui/jqueryui_datepicker.htm -->
		<script type="text/javascript">
			$(function() {
			   $("#datepicker-filter-employees").datepicker({
			      showOn: "button",
			      dateFormat: 'dd/mm/yy',
			      buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
			      buttonImageOnly: true,
			      buttonText: "Select a date"
			   });
			});

			$(document).ready(function() {
			    $("#submitFilterDayButton").click(function(event) {
			    	if ($('#datepicker-filter-employees').val().length == 0) {
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
			<br>
			<p>Select a day to filter</p>
			<form method="GET" action="EmployeeWorkingOnleave">
				<p>Day: <input type="text" name="datepickerFilter" id="datepicker-filter-employees" readOnly></p>
				<button id="submitFilterDayButton" type="submit">Submit</button>
			</form>
			<br>

			<p>Employees on holidays</p>
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
				</tr>
				<%
					@SuppressWarnings("unchecked")
					List<EmployeeDTO> employeesHoliday = (List<EmployeeDTO>) request.getAttribute("employeesHoliday");
					for (EmployeeDTO e : employeesHoliday) {
				%>
				<tr>
					<td><%=e.getFirstName()%></td>
					<td><%=e.getLastName()%></td>
					<td><%=e.getEmail()%></td>
					<td><%=e.getNameDep()%></td>
					<td><%=e.getNameEmpRole()%></td>
				</tr>
				<%
					}
				%>
			</table>
			<br>
	
			<p>Employees at work</p>
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>FirstName</th>
					<th>LastName</th>
					<th>Email</th>
					<th>Department</th>
					<th>Role</th>
				</tr>
				<%
					@SuppressWarnings("unchecked")
					List<EmployeeDTO> employeesWorking = (List<EmployeeDTO>) request.getAttribute("employeesWorking");
					for (EmployeeDTO e : employeesWorking) {
				%>
				<tr>
					<td><%=e.getFirstName()%></td>
					<td><%=e.getLastName()%></td>
					<td><%=e.getEmail()%></td>
					<td><%=e.getNameDep()%></td>
					<td><%=e.getNameEmpRole()%></td>
				</tr>
				<%
					}
				%>
			</table>
		<%
			} else {
				response.sendRedirect("/login.jsp");
			}
		%>
	</body>
</html>
