
$(document).ready(function() {
	$("#submitHolidayButton").click(function(event) {
		var startDate = $('#datepicker-start').val();
		var endDate = $('#datepicker-end').val();

		if (startDate.length == 0 || endDate.length == 0) {
    		event.preventDefault();
    	} else {
    		var isValid = true;
        	var urlRequest = 'http://localhost:8180/HolidayBookingAppWebApplication/CheckingHolidayContraints';

        	$.ajax({
        		type: 'GET',
        		async: false,
        		data: { startDate: startDate, endDate: endDate },
        		url: urlRequest,
        		success: function(result) {
        			var arrayErrors = result.split("[")[1];
        			var errorsArray = arrayErrors.split("]")[0];
        			var errors = errorsArray.split(",");

        			if (errors.length >= 0) {
            			$(".errorHolidayDaysRemaining").empty();
            	        $(".errorHolidayDaysRemaining").html(errors[0]);
            	        isValid = false;
        			}
        			if (errors.length >= 1) {
            			$(".errorHeadOrDeputyHeadAvailable").empty();
            	        $(".errorHeadOrDeputyHeadAvailable").html(errors[1]);
            	        isValid = false;
        			}
        			if (errors.length >= 2) {
            			$(".errorManagerAvailable").empty();
            	        $(".errorManagerAvailable").html(errors[2]);        				
            	        isValid = false;
        			}
        			if (errors.length >= 3) {
            	        $(".errorSeniorMemberAvailable").empty();
            	        $(".errorSeniorMemberAvailable").html(errors[3]);
            	        isValid = false;
        			}
        			if (errors.length >= 4) {
            	        $(".errorDepartmentSizeAvailable").empty();
            	        $(".errorDepartmentSizeAvailable").html(errors[4]);
            	        isValid = false;
        			}
        		}
        	});

        	if (isValid == false) {
            	event.preventDefault();
        	}
    	}
    });
});
