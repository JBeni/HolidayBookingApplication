
$(document).ready(function() {
	var isValid = false;
    $("#submitChangeButton").click(function(event) {
    	var oldPassword = $('#oldPassword').val();
    	var urlRequest = 'http://localhost:8180/HolidayBookingAppWebApplication/CheckOldUserPassword';

    	$.ajax({
    		type: 'GET',
    		async: false,
    		data: { oldPassword: oldPassword },
    		url: urlRequest,
    		success: function(result) {
    			isValid = result;
    		}
    	});

    	var newPassword = $('#newPassword').val();
    	var confirmNewPassword = $('#confirmNewPassword').val();
    	if (newPassword != confirmNewPassword) {
	    	$(".errorChangeNewPassword").empty();
	        $(".errorChangeNewPassword").append("<p>New Password and Confirm New Password are not the same.</p>");
    	} else {
	    	$(".errorChangeNewPassword").empty();
    	}

    	if (isValid == "false") {
	    	$(".errorChangePassword").empty();
	        $(".errorChangePassword").append("<p>Old Password is wrong.</p>");
	    	event.preventDefault();
    	} else {
	    	$(".errorChangePassword").empty();
    	}

    	if (newPassword != confirmNewPassword) {
	    	event.preventDefault();
    	}
    });
});
