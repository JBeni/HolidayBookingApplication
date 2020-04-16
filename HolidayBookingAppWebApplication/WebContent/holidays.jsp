<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.HolidayRequestDTO"%>
<%@ page import="entityclasses.HolidayBookingDTO"%>
<%@ page import="entityclasses.EmployeeDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Holidays list</title>
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

				$(function() {
				   $("#datepicker-filter-employees").datepicker({
				      showOn: "button",
				      dateFormat: 'dd/mm/yy',
				      buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
				      buttonImageOnly: true,
				      buttonText: "Select a date"
				   });
				});
		      </script>
		<!-- End of section "Cod taken" -->
	</head>

	<body>
		<%@ include file="menu.jsp" %>
		
		<%
			if (isUserAdmin != null) {
		%>

			<p>Make a holiday request.</p>
			<form method="POST" action="BookingRequestServlet">
				<p>Start Date: <input type="text" name="startDate" id="datepicker-start" disabled></p>
				<p>End Date: <input type="text" name="endDate" id="datepicker-end" disabled></p>
				<button type="submit">Submit</button>
			</form>
	
			<br><br><br>
	
		    <form class="form-inline my-2 my-lg-0" method="GET" action="BookingRequestServlet">
				<p>LastName: <input class="form-control mr-sm-2" type="text" name="lastNameFilter"></p>
				<p>FirstName: <input class="form-control mr-sm-2" type="text" name="firstNameFilter"></p>
				<p>Email: <input class="form-control mr-sm-2" type="text" name="emailFilter"></p>
				<p>Role: <input class="form-control mr-sm-2" type="text" name="roleEmployeeFilter"></p>
				<p>Department: <input class="form-control mr-sm-2" type="text" name="departmentEmployeeFilter"></p>
		      	<button class="btn btn-outline-success mb-2" type="submit">Search</button>
		    </form>
	
			<p>All your holidays request made.</p>
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>Holiday Begin Date</th>
					<th>Holiday End Date</th>
					<th>Duration</th>
					<th>Status</th>
	
					<%
						if (isUserAdmin == "admin") {
					%>
						<th>Actions</th>
					<%
						}
					%>
				</tr>
				<%
					@SuppressWarnings("unchecked")
					List<HolidayRequestDTO> allHolidayRequest = (List<HolidayRequestDTO>) request.getAttribute("holidayRequests");
					for (HolidayRequestDTO e : allHolidayRequest) {
				%>
				<tr>
					<td><%=e.getRequestBeginDate()%></td>
					<td><%=e.getRequestEndDate()%></td>
					<td><%=e.getHolidayDuration()%></td>
					<td><%=e.getStatusName()%></td>

					<%
						if (isUserAdmin == "admin") {
					%>
						<td>
							<%
							  if (e.getStatusName().equals("Pending")) {
							%>
								<a href="BookingServlet?method=POST&id=<%=e.getIdRequest()%>&status=Accepted">
									<button class="btn btn-warning">Accept</button>
								</a>
								<a href="BookingServlet?method=POST&id=<%=e.getIdRequest()%>&status=Rejected">
									<button class="btn btn-warning">Reject</button>
								</a>
							<%
							  } else {
							%>
								<p>No actions are possible.</p>
							<%
							  }
							%>
						</td>
					<%
						}
					%>
				</tr>
				<%
					}
				%>
			</table>
			<br><br>
	
			<p>All your holidays booking.</p>
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>Booking Begin Date</th>
					<th>Booking End Date</th>
					<th>Booking Duration</th>
				</tr>
				<%
					@SuppressWarnings("unchecked")
					List<HolidayBookingDTO> allHolidayBooking = (List<HolidayBookingDTO>) request.getAttribute("holidayBookings");
					for (HolidayBookingDTO e : allHolidayBooking) {
				%>
				<tr>
					<td><%=e.getBookingBeginDate()%></td>
					<td><%=e.getBookingEndDate()%></td>
					<td><%=e.getBookingDuration()%></td>
				</tr>
				<%
					}
				%>
			</table>
	
			<br><br><br>
			<form method="GET" action="BookingRequestServlet">
				<p>Day: <input type="text" name="datepicker-filter-employees" id="datepicker-filter-employees" disabled></p>
				<button type="submit">Submit</button>
			</form>
	
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

		<script type="text/javascript">
			<!-- code taken from https://stackoverflow.com/questions/12381563/how-to-stop-browser-back-button-using-javascript -->
				history.pushState(null, document.title, location.href);
				window.addEventListener('popstate', function (event)
				{
				  history.pushState(null, document.title, location.href);
				});
			<!-- area code taken -->
		</script>

	</body>
</html>
