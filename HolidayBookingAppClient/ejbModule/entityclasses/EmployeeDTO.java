package entityclasses;

import java.io.Serializable;
import java.util.Date;

public class EmployeeDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String homeAddress;
	private Date hireDate;
	private int holDaysEntitlement;
	private Integer salary;
	private String password;
	private boolean isDeleted;

	private int idEmpRole;
	private String nameEmpRole;
	private int idDep;
	private String nameDep;

	public EmployeeDTO(int id, String lastName, String firstName, String email, String phoneNumber, String homeAddress,
			Date hireDate, int holDaysEntitlement, Integer salary, String password, boolean isDeleted,
			int empRoleId, String empRoleName, int depId, String depName) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.homeAddress = homeAddress;
		this.hireDate = hireDate;
		this.holDaysEntitlement = holDaysEntitlement;
		this.salary = salary;
		this.password = password;
		this.isDeleted = isDeleted;

		this.idEmpRole = empRoleId;
		this.nameEmpRole = empRoleName;
		this.idDep = depId;
		this.nameDep = depName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public int getHolDaysEntitlement() {
		return holDaysEntitlement;
	}

	public void setHolDaysEntitlement(int holDaysEntitlement) {
		this.holDaysEntitlement = holDaysEntitlement;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getIdEmpRole() {
		return idEmpRole;
	}

	public void setIdEmpRole(int idEmpRole) {
		this.idEmpRole = idEmpRole;
	}

	public String getNameEmpRole() {
		return nameEmpRole;
	}

	public void setNameEmpRole(String nameEmpRole) {
		this.nameEmpRole = nameEmpRole;
	}

	public int getIdDep() {
		return idDep;
	}

	public void setIdDep(int idDep) {
		this.idDep = idDep;
	}

	public String getNameDep() {
		return nameDep;
	}

	public void setNameDep(String nameDep) {
		this.nameDep = nameDep;
	}

}
