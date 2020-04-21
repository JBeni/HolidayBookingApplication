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
	</head>

	<body>
		<%@ include file="menu.jsp" %>
		
		<%
			if (isUserLogged != null) {
		%>
			<div>
				<a href="NewHolidayRequest">
				<button class="btn btn-warning mt-3">Add New Holiday Request</button></a>
			</div>
			<br>

			<%
				if (isUserLogged == "admin") {
			%>
			    <form class="form-inline my-2 my-lg-0" method="GET" action="BookingRequestServlet">
					<p>LastName: <input class="form-control mr-sm-2" type="text" name="lastNameFilter"></p>
					<p>FirstName: <input class="form-control mr-sm-2" type="text" name="firstNameFilter"></p>
					<p>Email: <input class="form-control mr-sm-2" type="text" name="emailFilter"></p>
					<p>Role: <input class="form-control mr-sm-2" type="text" name="roleEmployeeFilter"></p>
					<p>Department: <input class="form-control mr-sm-2" type="text" name="departmentEmployeeFilter"></p>
			      	<button class="btn btn-outline-success mb-2" type="submit">Search</button>
			    </form>
			<%
				}
			%>

			<%
				if (isUserLogged == "admin") {
			%>
					<p>All the holidays request made.</p>
			<%
				} else if (isUserLogged == "standard-user") {
			%>
					<p>All your holidays request made.</p>					
			<%
				}
			%>
			<table class="table table-bordered table-striper table-hover">
				<tr>
					<th>Holiday Begin Date</th>
					<th>Holiday End Date</th>
					<th>Duration</th>
					<th>Status</th>
	
					<%
						if (isUserLogged == "admin") {
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
						if (isUserLogged == "admin") {
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

			<%
				if (isUserLogged == "admin") {
			%>
					<p>All the holidays booking.</p>
			<%
				} else if (isUserLogged == "standard-user") {
			%>
					<p>All your holidays booking made.</p>
			<%
				}
			%>
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
			<br>
		<%
			} else {
				response.sendRedirect("/login.jsp");
			}
		%>
	</body>
</html>
