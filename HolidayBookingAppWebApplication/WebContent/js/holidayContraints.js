
$(document).ready(function() {
	$("#submitHolidayButton").click(function(event) {
		var startDate = $('#datepicker-start').val();
		var endDate = $('#datepicker-end').val();

		if (startDate.length == 0 || endDate.length == 0) {
    		event.preventDefault();
    	} else {
        	var urlRequest = 'http://localhost:8180/HolidayBookingAppWebApplication/CheckingHolidayContraints';

        	$.ajax({
        		type: 'GET',
        		async: false,
        		data: { startDate: startDate, endDate: endDate },
        		url: urlRequest,
        		success: function(result) {
        			console.log(result);
        		}
        	});
        	debugger;
    	}
    });
});
