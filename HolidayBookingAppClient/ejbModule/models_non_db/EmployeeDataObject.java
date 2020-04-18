package models_non_db;

import java.io.Serializable;

public class EmployeeDataObject implements Serializable {
    private static final long serialVersionUID = 1L;

	public EmployeeDataObject() {
	}

	public boolean error;
	public String lastName;
	public String firstName;
	public String email;
	public String phoneNumber;
	public String homeAddress;
	public String hireDate;
	public String holDaysEntitlement;
	public String salary;
	public String password;
	public String department;
	public String role;
}
