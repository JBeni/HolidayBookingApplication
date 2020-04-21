<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>404 Not Found</title>
	</head>

	<body>
		<p>Something went wrong please try again..</p>

		<div>
			<div>
				<a href="HolidaySystemAppServlet">
				<button class="btn btn-warning mt-3">Go to Login Page</button></a>
			</div>
		</div>


		<!-- code taken from https://stackoverflow.com/questions/12381563/how-to-stop-browser-back-button-using-javascript -->
		<script type="text/javascript">
			history.pushState(null, document.title, location.href);
			window.addEventListener('popstate', function (event) {
			  history.pushState(null, document.title, location.href);
			});
		</script>
	</body>
</html>
