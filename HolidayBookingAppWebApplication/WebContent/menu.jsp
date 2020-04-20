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
			String isUserAdmin = (String) session.getAttribute("username");
			if (isUserAdmin != null) {
		%>
			<div class="container-fluid">
				<div class="navbar-header">
			    	<%
						if (isUserAdmin == "admin") {
				    %>
						<a class="navbar-brand" href="EmployeesServlet">Employee</a>
			    	<%
						}
			    	%>
					<a class="navbar-brand" href="BookingRequestServlet">Holiday Requests</a>
				</div>
			    <div class="form-inline my-2 my-lg-0">
			    	<%
						if (isUserAdmin == "admin") {
			    	%>
						<a class="navbar-brand" href="BookingRequestServlet">Notifications</a>
			    	<%
						}
			    	%>
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
