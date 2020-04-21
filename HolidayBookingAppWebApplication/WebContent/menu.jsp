<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Holiday Booking App</title>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
	</head>

	<body>
		<nav class="navbar navbar-inverse navbar-dark bg-dark">
			<%
				String isUserLogged = (String) session.getAttribute("username");
				if (isUserLogged != null) {
			%>
				<div class="container-fluid">
					<div class="navbar-header">
				    	<%
							if (isUserLogged == "admin") {
					    %>
							<a class="navbar-brand" href="EmployeesServlet">Employee</a>
				    	<%
							}
				    	%>
							<a class="navbar-brand" href="BookingRequestServlet">Holiday Requests</a>
				    	<%
							if (isUserLogged == "admin") {
					    %>
							<a class="navbar-brand" href="EmployeeWorkingOnleave">Employee Working/On leave</a>
				    	<%
							}
				    	%>
					</div>
				    <div class="form-inline my-2 my-lg-0">
						<a class="navbar-brand" href="ChangePasswordServlet">Change Password</a>
						<a class="navbar-brand" href="LogoutServlet">Logout</a>
			    	</div>
				</div>
			<%
				} else {
					response.sendRedirect("/login.jsp");
				}
			%>
		</nav>

		<!-- code taken from https://stackoverflow.com/questions/12381563/how-to-stop-browser-back-button-using-javascript -->
		<script type="text/javascript">
			history.pushState(null, document.title, location.href);
			window.addEventListener('popstate', function (event) {
			  history.pushState(null, document.title, location.href);
			});
		</script>
	</body>
</html>
