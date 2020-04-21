<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.EmployeeDTO"%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Employee Page</title>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
		<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
		<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
		<link href="css/styles.css" rel="stylesheet" type="text/css">

		<script type="text/javascript">
			$(document).ready(function() {
		    	$('#deletedEmployees').hide();

		    	$("#showDeleteEmployeesButton").click(function() {
			    	$('#deletedEmployees').show();
			    });
		    	$("#hideDeleteEmployeesButton").click(function() {
			    	$('#deletedEmployees').hide();
			    });
			});
		</script>
	</head>

<body>
	<%@ include file="menu.jsp" %>

	<div>
	<%
		if (isUserLogged != "admin" && isUserLogged == null) {
			response.sendRedirect("HolidaySystemAppServlet");
		} else if (isUserLogged != "admin" && isUserLogged != null) {
			response.sendRedirect("EmployeesServlet");
		}
	%>
		<div>
			<a href="NewEmployeeServlet">
			<button class="btn btn-warning mt-3">Add New Employee</button></a>
		</div>
	</div>

	<table class="table table-bordered table-striper table-hover mt-3">
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
	<p>
		<button id="showDeleteEmployeesButton">Show</button>
		<button id="hideDeleteEmployeesButton">Hide</button>
	</p>
	<table id="deletedEmployees" class="table table-bordered table-striper table-hover">
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
