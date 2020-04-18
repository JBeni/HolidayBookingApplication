<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.*"%>
<%@ page import="entityclasses.DepartmentDTO"%>
<%@ page import="entityclasses.EmployeeRoleDTO"%>
<%@ page import="models_non_db.EmployeeDataObject"%>

<!DOCTYPE html>
<html>
<head>
	<title>Add new Employee</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

	<link href = "https://code.jquery.com/ui/1.10.4/themes/ui-lightness/jquery-ui.css" rel="stylesheet">
	<script src = "https://code.jquery.com/jquery-1.10.2.js"></script>
	<script src = "https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

	  <!-- Cod taken from https://www.tutorialspoint.com/jqueryui/jqueryui_datepicker.htm -->
      <script type="text/javascript">
		$(function() {
		   $("#datepicker").datepicker({
		      showOn: "button",
		      dateFormat: 'dd/mm/yy',
		      buttonImage: "https://jqueryui.com/resources/demos/datepicker/images/calendar.gif",
		      buttonImageOnly: true,
		      buttonText: "Select a date"
		   });
		});
      </script>

	<script type="text/javascript">
		window.onload = function() {
			$('#select-role').attr('disabled','disabled');
		}

		function changeFunc() {
			$('#select-role').attr('disabled','disabled');
		}
	</script>
</head>

<body class="h-100">
	<%@ include file="menu.jsp" %>

	<%
		String isLogin = (String) session.getAttribute("username");
		if (isLogin != "admin") {
			response.sendRedirect("HolidaySystemAppServlet");
		}
	%>
	<div class="row">
		<div class="col-md-6">
			<h3>New employee</h3>
		</div>
	</div>

	<form method="POST" action="NewEmployeeServlet">
		<div class="form-group">
			<label>Last name</label>
			<input class="form-control" name="lastName" placeholder="Last_name" />
			<p style="color: red;">${employeeData.firstName}</p>
			<p style="color: red;">${employeeData.firstName}</p>
		</div>
		<div class="form-group">
			<label>First name</label>
			<input class="form-control" name="firstName" placeholder="First_name" />
		</div>
		<div class="form-group">
			<label>Email</label>
			<input class="form-control" name="email" placeholder="email" />
		</div>
		<div class="form-group">
			<label>Phone number</label>
			<input class="form-control" name="phoneNumber" placeholder="Phone_number" />
		</div>
		<div class="form-group">
			<label>Home address</label>
			<input class="form-control" name="homeAddress" placeholder="Home_address" />
		</div>
		<div class="form-group">
			<label>Hire date</label>
			<p>Date: <input type="text" readOnly name="hireDatePicker" id="datepicker"></p>
		</div>
		<div class="form-group">
			<label>Holiday Days Entitlement</label>
			<input class="form-control" name="holDaysEntitlement" placeholder="Holiday Days Entitlement" />
		</div>
		<div class="form-group">
			<label>Salary</label>
			<input class="form-control" name="salary" placeholder="Salary" />
		</div>
		<div class="form-group">
			<label>Password</label>
			<input class="form-control" name="password" placeholder="password" />
		</div>

		<div class="form-group">
			<label>Select a Department</label>
			<select class="select form-control" name="selectedEmployeeDepartment" onchange="changeFunc();">
				<option class="form-control" value="" selected="selected" disabled="disabled">Select an department:</option>
				<%
					@SuppressWarnings("unchecked")
					List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute("departments");
					for (DepartmentDTO e : departments) {
				%>
						<option class="form-control" value="<%=e.getIdDep()%>"><%=e.getNameDep()%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="form-group">
			<label>Select a Role</label>
			<select id="select-role" class="select form-control" name="selectedEmployeeRole">
				<option class="form-control" value="" selected="selected" disabled="disabled">Select a role:</option>
				<%
					@SuppressWarnings("unchecked")
					List<EmployeeRoleDTO> employeeRoles = (List<EmployeeRoleDTO>) request.getAttribute("employeeRoles");
					for (EmployeeRoleDTO e : employeeRoles) {
				%>
						<option class="form-control" value="<%=e.getIdEmpRole()%>"><%=e.getNameEmpRole()%></option>
				<%
					}
				%>
			</select>
		</div>

		<button style="margin-bottom: 10px;" type="submit">Submit</button>
		<a href="EmployeesServlet">Back</a>
	</form>

</body>
</html>
