package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.EmployeeDTO;
import model.TDepartment;
import model.TEmployee;
import model.TEmployeeRole;

@Stateless
@LocalBean
public class EmployeeAppBean implements EmployeeAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public EmployeeAppBean() {
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
	public List<EmployeeDTO> getEmployeesByDepartmentId(int id) {
		@SuppressWarnings("unchecked")
		List<TEmployee> allEmployees = entityManager.createQuery("SELECT e FROM TEmployee e WHERE e.department.idDep = :id")
				.setParameter("id", id).getResultList();
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
	public boolean addEmployee(EmployeeDTO newEmp) {
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
	public boolean updateEmployee(EmployeeDTO updateEmp) {
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
	public boolean updatePassWord(EmployeeDTO updateEmpPassword) {
		try {
			TEmployee employee = entityManager.find(TEmployee.class, updateEmpPassword.getId());

			employee.setPassword(updateEmpPassword.getPassword());

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

	@Override
	public boolean checkUserPassword(int userId, String oldPassword) {
		TEmployee employee = (TEmployee) entityManager.createQuery("SELECT e FROM TEmployee e WHERE e.id = :id")
				.setParameter("id", userId).getResultList().get(0);
		if (employee.getPassword().equals(oldPassword)) {
			return true;
		}
		return false;
	}

}
