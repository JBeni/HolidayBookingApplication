<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.EmployeeDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>

<body>
	<%@ include file="menu.jsp" %>

	<div>
	<%
		String isLogin = (String) session.getAttribute("username");
		if (isLogin != "admin") {
			response.sendRedirect("HolidaySystemAppServlet");
		}
	%>
		<div class="row">
			<div class="col-md-6">
				<h3>Employees</h3>
			</div>
		</div>

		<div>
			<a href="NewEmployeeServlet">
			<button class="btn btn-warning">Add New Employee</button></a>
		</div>
		<div>
			<a href="BookingRequestServlet">
				<button class="btn btn-warning">Holidays</button>
			</a>
		</div>
	</div>
	<table class="table table-bordered table-striper table-hover">
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Department</th>
			<th>Role</th>
			<th class="text-center">Actions</th>
		</tr>
		<%
			@SuppressWarnings("unchecked")
			List<EmployeeDTO> allEmployees = (List<EmployeeDTO>) request.getAttribute("allEmployees");
			for (EmployeeDTO e : allEmployees) {
		%>
		<tr>
			<td><%=e.getFirstName()%></td>
			<td><%=e.getLastName()%></td>
			<td><%=e.getEmail()%></td>
			<td><%=e.getNameDep()%></td>
			<td><%=e.getNameEmpRole()%></td>			
			<td>
				<a href="EditEmployeeServlet?id=<%=e.getId()%>">
					<button class="btn btn-warning">Edit</button>
				</a>
				<a href="DeleteEmployeeServlet?id=<%=e.getId()%>">
					<button class="btn btn-warning">Delete</button>
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<p>Show all deleted users</p>
	<table class="table table-bordered table-striper table-hover">
		<tr>
			<th>FirstName</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Department</th>
			<th>Role</th>
			<th class="text-center">Actions</th>
		</tr>
		<%
			@SuppressWarnings("unchecked")
			List<EmployeeDTO> allDeletedEmployees = (List<EmployeeDTO>) request.getAttribute("allDeletedEmployees");
			for (EmployeeDTO e : allDeletedEmployees) {
		%>
		<tr>
			<td><%=e.getFirstName()%></td>
			<td><%=e.getLastName()%></td>
			<td><%=e.getEmail()%></td>
			<td><%=e.getNameDep()%></td>
			<td><%=e.getNameEmpRole()%></td>
			<td>
				<a href="EditEmployeeServlet?id=<%=e.getId()%>">
					<button class="btn btn-warning">Edit</button>
				</a>
			</td>
		</tr>
		<%
			}
		%>
	</table>

</body>
</html>
