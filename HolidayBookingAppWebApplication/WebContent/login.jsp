<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Holiday Booking Application</title>

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	</head>

	<body>
		<h1>Holiday Booking Application</h1>
		<br>

		<%
			String userLogged = (String) session.getAttribute("username");
			if (userLogged == null) {
				String errMsg = (String) request.getAttribute("errorLoginMessage");
				if (errMsg != null) {
					out.print("<h5>" + errMsg + "</h5>");
				}
				String isLogin = (String) session.getAttribute("username");
				if (isLogin != null) {
					response.sendRedirect("EmployeesServlet");
				}
		%>
			<div class="col-sm-3 first_cont">
				<form class="form-signin" method="POST" action="HolidaySystemAppServlet">
		            <!--<input type="email" class="form-control" name="email" placeholder="Email address" required>-->

					<input type="text" class="form-control" name="email" placeholder="Email address" required>
					<p></p>
					<input type="password" class="form-control" name="password"	placeholder="Password" required>
					<p></p>
					<button class="btn-lg btn-primary btn-block" type="submit" value="Login">Sign in</button>
				</form>
			</div>
		<%
			} else {
		%>
				<p>User already logged in.</p>
		<%
				response.sendRedirect("EmployeesServlet");
			}
		%>

		<!-- code taken from https://stackoverflow.com/questions/12381563/how-to-stop-browser-back-button-using-javascript -->
		<script type="text/javascript">
			history.pushState(null, document.title, location.href);
			window.addEventListener('popstate', function (event)
			{
			  history.pushState(null, document.title, location.href);
			});
		</script>
	</body>
</html>
