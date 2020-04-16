package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.DepartmentDTO;
import entityclasses.EmployeeDTO;
import entityclasses.EmployeeRoleDTO;
import model.TDepartment;
import model.TEmployee;
import model.TEmployeeRole;

@Stateless
@LocalBean
public class HolidaySystemAppBean implements HolidaySystemAppBeanRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public HolidaySystemAppBean() {
	}

	@Override
	public boolean login(String email, String password) throws Exception {
		@SuppressWarnings("unchecked")
		List<TEmployee> existsUser = entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.email = :email and e.password = :password")
				.setParameter("email", email).setParameter("password", password).getResultList();

		//0 or 1 to check access privileges
		if (!existsUser.isEmpty()) {
			if (existsUser.get(0).getEmployeeRole().getIdEmpRole() == 1) {
				return true;
			}

			return false;
		} else {
			throw new Exception("User does not exists.");
		}
	}

	@Override
	public EmployeeDTO getEmployeeById(int id) {
		@SuppressWarnings("unchecked")
		List<TEmployee> queryResult = entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.id = :id")
				.setParameter("id", id).getResultList();
		List<EmployeeDTO> employeeDTO = new ArrayList<>();
		for (TEmployee e : queryResult) {
			employeeDTO.add(new EmployeeDTO(e.getId(), e.getLastName(), e.getFirstName(), e.getEmail(), e.getPhoneNumber(),
					e.getHomeAddress(), e.getHireDate(), e.getHolDaysEntitlement(), e.getSalary(), e.getPassword(), e.getIsDeleted(),
					e.getEmployeeRole().getIdEmpRole(), e.getEmployeeRole().getNameEmpRole(),
					e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return employeeDTO.get(0);
	}

	@Override
	public EmployeeDTO getEmployeeByEmail(String email) {
		@SuppressWarnings("unchecked")
		List<TEmployee> queryResult = entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.email = :email")
				.setParameter("email", email).getResultList();
		List<EmployeeDTO> employeeDTO = new ArrayList<>();
		for (TEmployee e : queryResult) {
			employeeDTO.add(new EmployeeDTO(e.getId(), e.getLastName(), e.getFirstName(), e.getEmail(), e.getPhoneNumber(),
					e.getHomeAddress(), e.getHireDate(), e.getHolDaysEntitlement(), e.getSalary(), e.getPassword(),
					e.getIsDeleted(), e.getEmployeeRole().getIdEmpRole(), e.getEmployeeRole().getNameEmpRole(),
					e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return employeeDTO.get(0);
	}

	@Override
	public List<DepartmentDTO> getDepartments() {
		@SuppressWarnings("unchecked")
		List<TDepartment> departments = entityManager.createNamedQuery("TDepartment.findAll").getResultList();
		List<DepartmentDTO> departmentsDTO = new ArrayList<>();
		for (TDepartment e : departments) {
			departmentsDTO.add(new DepartmentDTO(e.getIdDep(), e.getNameDep(), e.getNameDepUpper(), e.getMaxNPeople()));
		}
		return departmentsDTO;
	}

	@Override
	public List<EmployeeRoleDTO> getRoles() {
		@SuppressWarnings("unchecked")
		List<TEmployeeRole> employeeRoles = entityManager.createNamedQuery("TEmployeeRole.findAll").getResultList();
		List<EmployeeRoleDTO> employeeRolesDTO = new ArrayList<>();
		for (TEmployeeRole e : employeeRoles) {
			employeeRolesDTO.add(new EmployeeRoleDTO(e.getIdEmpRole(), e.getNameEmpRole(), e.getNameEmpRoleUpper(), e.getCanApprove()));
		}
		return employeeRolesDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		@SuppressWarnings("unchecked")
		List<TEmployee> allEmployees = entityManager.createNamedQuery("TEmployee.findAll").getResultList();
		List<EmployeeDTO> allEmployeesDTO = new ArrayList<>();
		for (TEmployee e : allEmployees) {
			allEmployeesDTO.add(new EmployeeDTO(e.getId(), e.getLastName(), e.getFirstName(), e.getEmail(), e.getPhoneNumber(),
					e.getHomeAddress(), e.getHireDate(), e.getHolDaysEntitlement(), e.getSalary(), e.getPassword(),
					e.getIsDeleted(), e.getEmployeeRole().getIdEmpRole(), e.getEmployeeRole().getNameEmpRole(),
					e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return allEmployeesDTO;
	}

	@Override
	public List<EmployeeDTO> getAllDeletedEmployees() {
		@SuppressWarnings("unchecked")
		List<TEmployee> allDeletedEmployees = entityManager.createNamedQuery("TEmployee.findAllDeleted").getResultList();
		List<EmployeeDTO> allDeletedEmployeesDTO = new ArrayList<>();
		for (TEmployee e : allDeletedEmployees) {
			allDeletedEmployeesDTO.add(new EmployeeDTO(e.getId(), e.getLastName(), e.getFirstName(), e.getEmail(), e.getPhoneNumber(),
					e.getHomeAddress(), e.getHireDate(), e.getHolDaysEntitlement(), e.getSalary(), e.getPassword(),
					e.getIsDeleted(), e.getEmployeeRole().getIdEmpRole(), e.getEmployeeRole().getNameEmpRole(),
					e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return allDeletedEmployeesDTO;
	}

	@Override
	public boolean addNewEmployee(EmployeeDTO newEmp) {
		try {
			TEmployeeRole employeeRole = entityManager.find(TEmployeeRole.class, newEmp.getIdEmpRole());
			TDepartment department = entityManager.find(TDepartment.class, newEmp.getIdDep());
			TEmployee newEmployee = new TEmployee(newEmp.getId(), newEmp.getLastName(), newEmp.getFirstName(), newEmp.getEmail(),
					newEmp.getPhoneNumber(), newEmp.getHomeAddress(), newEmp.getHireDate(), newEmp.getHolDaysEntitlement(),
					newEmp.getSalary(), newEmp.getPassword(), newEmp.getIsDeleted(), employeeRole, department);
			entityManager.persist(newEmployee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editEmployee(EmployeeDTO updateEmp) {
		try {
			TEmployee employee = entityManager.find(TEmployee.class, updateEmp.getId());
			TEmployeeRole employeeRole = entityManager.find(TEmployeeRole.class, updateEmp.getIdEmpRole());
			TDepartment department = entityManager.find(TDepartment.class, updateEmp.getIdDep());

			employee.setLastName(updateEmp.getLastName());
			employee.setFirstName(updateEmp.getFirstName());
			employee.setEmail(updateEmp.getEmail());
			employee.setPhoneNumber(updateEmp.getPhoneNumber());
			employee.setHomeAddress(updateEmp.getHomeAddress());
			employee.setHolDaysEntitlement(updateEmp.getHolDaysEntitlement());
			employee.setSalary(updateEmp.getSalary());
			employee.setIsDeleted(updateEmp.getIsDeleted());

			employee.setEmployeeRole(employeeRole);
			employee.setDepartment(department);

			entityManager.merge(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(EmployeeDTO deleteEmp) {
		try {
			TEmployee employee = entityManager.find(TEmployee.class, deleteEmp.getId());
			employee.setIsDeleted(true);
			entityManager.merge(employee);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
