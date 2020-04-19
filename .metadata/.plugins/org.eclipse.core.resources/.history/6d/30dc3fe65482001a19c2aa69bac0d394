
$(document).ready(function() {
    $("#submitButton").click(function(event){
        var isValid = validateEmployeeAddForm();
        if(!isValid){
            event.preventDefault();
        }
    });
});

function validateEmployeeAddForm() {
	var sw = false;

	var lastName = $("#lastName").val();
    var firstName = $("#firstName").val();
    var email = $("#email").val();
    var phoneNumber = $("#phoneNumber").val();
    var homeAddress = $("#homeAddress").val();
    var hireDate = $("#datepicker").val();
    var holDaysEntitlement = $("#holDaysEntitlement").val();
    var salary = $("#salary").val();
    var password = $("#password").val();
    var department = $("#selectedEmployeeDepartment").val();
    var role = $("#selectedEmployeeRole").val();

    if(lastName.length == 0) {
    	if($(".errorLastName p").val() == undefined) {
            $(".errorLastName").append("<p>Last name is required.</p>")
    	}
        sw = true;
    } else {
        $(".errorLastName").empty();
        sw = false;
    }

    if(firstName.length == 0) {
        $(".errorFirstName").append("<p>First name is required.</p>")
        sw = true;
    } else {
        $(".errorFirstName").empty();
        sw = false;
    }

    if(email.length == 0) {
        $(".errorEmail").append("<p>Email is required.</p>")
        sw = true;
    } else {
        $(".errorEmail").empty();
        sw = false;
    }

    if(phoneNumber.length == 0) {
        $(".errorPhoneNumber").append("<p>Phone number is required.</p>")
        sw = true;
    } else {
        $(".errorPhoneNumber").empty();
        sw = false;
    }

    if(homeAddress.length == 0) {
        $(".errorHomeAddress").append("<p>Home address is required.</p>")
        sw = true;
    } else {
        $(".errorHomeAddress").empty();
        sw = false;
    }

    if(hireDate.length == 0) {
        $(".errorHireDate").append("<p>Hire date is required.</p>")
        sw = true;
    } else {
        $(".errorHireDate").empty();
        sw = false;
    }

    if(holDaysEntitlement.length == 0) {
        $(".errorHolDaysEntitlement").append("<p>Holiday Days Entitlement is required.</p>")
        sw = true;
    } else {
        $(".errorHolDaysEntitlement").empty();
        sw = false;
    }

    if(salary.length == 0) {
        $(".errorSalary").append("<p>Salary is required.</p>")
        sw = true;
    } else {
        $(".errorSalary").empty();
        sw = false;
    }

    if(password.length == 0) {
        $(".errorPassword").append("<p>Password is required.</p>")
        sw = true;
    } else {
        $(".errorPassword").empty();
        sw = false;
    }

    if(department == null) {
        $(".errorDepartment").append("<p>Department is required.</p>")
        sw = true;
    } else {
        $(".errorDepartment").empty();
        sw = false;
    }

    if(role == undefined) {
        $(".errorRole").append("<p>Role is required.</p>")
        sw = true;
    } else {
        $(".errorRole").empty();
        sw = false;
    }

}

