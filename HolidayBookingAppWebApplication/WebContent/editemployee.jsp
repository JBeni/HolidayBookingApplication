<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="entityclasses.EmployeeDTO"%>
<%@ page import="entityclasses.EmployeeRoleDTO"%>
<%@ page import="entityclasses.DepartmentDTO"%>

<!DOCTYPE html>
<html class="h-100">
<head>
	<title>Edit Employee</title>
	<link rel="stylesheet" href="css/bootstrap_3.7.css">
</head>

<body>
	<%@ include file="menu.jsp" %>

	<div class="row">
		<div class="col-md-6">
			<h3>Employee edit</h3>
		</div>
	</div>
	<%
		String isLogin = (String) session.getAttribute("username");
		if (isLogin != "admin") {
			response.sendRedirect("HolidaySystemAppServlet");
		}
	%>

	<form method="POST" action="EditEmployeeServlet">
		<input type="hidden" name="id" value="${employee.id}">
		<div class="form-group">
			<label>Last name</label>
			<input class="form-control" name="lastName" placeholder="Last_name" value="${employee.lastName}" />
		</div>
		<div class="form-group">
			<label>First name</label>
			<input class="form-control"	name="firstName" placeholder="First_name" value="${employee.firstName}" />
		</div>
		<div class="form-group">
			<label>Email</label>
			<input class="form-control" name="email" placeholder="email" value="${employee.email}" />
		</div>
		<div class="form-group">
			<label>Phone number</label>
			<input class="form-control" name="phoneNumber" placeholder="Phone_number" value="${employee.phoneNumber}" />
		</div>
		<div class="form-group">
			<label>Home address</label>
			<input class="form-control" name="homeAddress" placeholder="Home_address" value="${employee.homeAddress}" />
		</div>
		<div class="form-group">
			<label>Hire date</label>
			<input class="form-control" name="hireDate" placeholder="Hire_date" value="${employee.hireDate}" disabled/>
		</div>
		<div class="form-group">
			<label>Holiday Days Entitlement</label>
			<input class="form-control" name="holDaysEntitlement" placeholder="Holiday Days Entitlement" value="${employee.holDaysEntitlement}" disabled />
		</div>
		<div class="form-group">
			<label>Salary</label>
			<input class="form-control" name="salary" placeholder="Salary" value="${employee.salary}" />
		</div>
		<div class="form-group">
			<label>Password</label>
			<input class="form-control" name="password" placeholder="password" value="${employee.password}" />
		</div>
		<div class="form-group">
			<label>Is Deleted</label>
			<%
				boolean isDeleted = (boolean) session.getAttribute("isDeleted");
				if (isDeleted == false) {
			%>
					<input class="form-control" name="isDeleted" placeholder="true or false" value="${employee.isDeleted}" disabled/>
			<%
				} else {
			%>
					<input class="form-control" name="isDeleted" placeholder="true or false" value="${employee.isDeleted}" />
			<%
				}
			%>
		</div>

		<div class="form-group">
			<label>Select a Role</label>
			<select class="select form-control" name="selectedEmployeeRole">
				<option class="form-control" value="${employee.idEmpRole}" selected="selected">${employee.nameEmpRole}</option>
				<%
					@SuppressWarnings("unchecked")
					List<EmployeeRoleDTO> employeeRoles = (List<EmployeeRoleDTO>) request.getAttribute("employeeRoles");
					for (EmployeeRoleDTO e : employeeRoles) {
				%>
						<option value="<%=e.getIdEmpRole()%>"><%=e.getNameEmpRole()%></option>
				<%
					}
				%>
			</select>
		</div>
		<div class="form-group">
			<label>Select a Department</label>
			<select class="select form-control" name="selectedEmployeeDepartment">
				<option value="${employee.idDep}" selected="selected">${employee.nameDep}</option>
				<%
					@SuppressWarnings("unchecked")
					List<DepartmentDTO> departments = (List<DepartmentDTO>) request.getAttribute("departments");
					for (DepartmentDTO e : departments) {
				%>
						<option value="<%=e.getIdDep()%>"><%=e.getNameDep()%></option>
				<%
					}
				%>
			</select>
		</div>

		<button type="submit">Submit</button>
		<a href="EmployeesServlet">Back</a>
	</form>
</body>
</html>
