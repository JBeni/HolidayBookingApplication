
$(document).ready(function() {
    $("#submitUpdateButton").click(function(event) {
        var isInvalid = validateEmployeeUpdateForm();
        if (isInvalid) {
            event.preventDefault();
        }
    });
});

function validateEmployeeUpdateForm() {
	var sw = false;

	var lastName = $("#lastName").val();
    var firstName = $("#firstName").val();
    var email = $("#email").val();
    var phoneNumber = $("#phoneNumber").val();
    var homeAddress = $("#homeAddress").val();
    var salary = $("#salary").val();
    var department = $("#selectedEmployeeDepartment").val();
    var role = $("#selectedEmployeeRole").val();

    if (lastName.length == 0) {
        $(".errorLastName").empty();
        $(".errorLastName").append("<p>Last name is required.</p>")
        sw = true;
    } else {
        $(".errorLastName").empty();
    }

    if (firstName.length == 0) {
        $(".errorFirstName").empty();
        $(".errorFirstName").append("<p>First name is required.</p>")
        sw = true;
    } else {
        $(".errorFirstName").empty();
    }

    if (email.length == 0) {
        $(".errorEmail").empty();
        $(".errorEmail").append("<p>Email is required.</p>")
        sw = true;
    } else {
        $(".errorEmail").empty();
    }

    if (phoneNumber.length == 0) {
        $(".errorPhoneNumber").empty();
        $(".errorPhoneNumber").append("<p>Phone number is required.</p>")
        sw = true;
    } else {
        $(".errorPhoneNumber").empty();
    }

    if (homeAddress.length == 0) {
        $(".errorHomeAddress").empty();
        $(".errorHomeAddress").append("<p>Home address is required.</p>")
        sw = true;
    } else {
        $(".errorHomeAddress").empty();
    }

    if (salary.length == 0) {
    	$(".errorSalary").empty();
        $(".errorSalary").append("<p>Salary is required.</p>");
        sw = true;
    } else if (isNaN(salary))  {
    	$(".errorSalary").empty();
        $(".errorSalary").append("<p>Salary must be a number.</p>");
        sw = true;
    } else {
    	$(".errorSalary").empty();
    }

    if (department == null) {
        $(".errorDepartment").empty();
        $(".errorDepartment").append("<p>Department is required.</p>");
        sw = true;
    } else {
        $(".errorDepartment").empty();
    }

    if (role == null) {
        $(".errorRole").empty();
        $(".errorRole").append("<p>Role is required.</p>");
        sw = true;
    } else {
        $(".errorRole").empty();
    }

    return sw;
}
