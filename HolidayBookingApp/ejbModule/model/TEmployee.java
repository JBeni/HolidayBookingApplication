package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


@Entity
@Table(name = "T_EMPLOYEE")
@NamedQuery(name = "TEmployee.findAll", query = "SELECT t FROM TEmployee t where t.isDeleted = false")
@NamedQuery(name = "TEmployee.findAllDeleted", query = "SELECT t FROM TEmployee t where t.isDeleted = true")
public class TEmployee implements Serializable {
	private static final long serialVersionUID = 1L;

	public TEmployee(int id, String lastName, String firstName, String email, String phoneNumber, String homeAddress,
			Date hireDate, int holDaysEntitlement, Integer salary, String password, boolean isDeleted,
			TEmployeeRole employeeRole, TDepartment department) {
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
		
		this.employeeRole = employeeRole;
		this.department = department;
	}

	public TEmployee() {
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEP", nullable = false)
	private TDepartment department;

	public TDepartment getDepartment() {
		return department;
	}

	public void setDepartment(TDepartment department) {
		this.department = department;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_EMP_ROLE", nullable = false)
	private TEmployeeRole employeeRole;

	public TEmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(TEmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMP")
	private int id;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "PHONE_NUMBER")
	private String phoneNumber;

	@Column(name = "HOME_ADDRESS")
	private String homeAddress;

	@Column(name = "HIRE_DATE")
	private Date hireDate;

	@Column(name = "HOL_DAYS_ENTITLEMENT")
	private int holDaysEntitlement;

	@Column(name = "SALARY")
	private Integer salary;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "IsDeleted")
	private boolean isDeleted;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public int getHolDaysEntitlement() {
		return holDaysEntitlement;
	}

	public void setHolDaysEntitlement(int holDaysEntitlement) {
		this.holDaysEntitlement = holDaysEntitlement;
	}

	public boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}
