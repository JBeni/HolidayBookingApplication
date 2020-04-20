<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<link href="css/styles.css" rel="stylesheet" type="text/css">

		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<script src="js/changePassword.js"></script>
	</head>

	<body>
		<%@ include file="menu.jsp" %>

		<div class="col-sm-3 first_cont container center_div" style="margin-top: 80px;">
			<form class="form-signin" method="POST" action="ChangePasswordServlet">
				<input type="password" class="form-control" id="oldPassword" name="oldPassword" placeholder="Old Password" required>
				<div class="errorChangePassword"></div>
				<br>
				<br>
				<input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="New Password" required>
				<br>
				<input type="password" class="form-control" id="confirmNewPassword" name="confirmNewPassword" placeholder="Confirm New Password" required>
				<div class="errorChangeNewPassword"></div>
				<br>

				<button id="submitChangeButton" class="btn-lg btn-primary btn-block" type="submit" value="Login">Change Password</button>
			</form>
			<br>

			<p id="result"></p>
		</div>
	</body>
</html>
