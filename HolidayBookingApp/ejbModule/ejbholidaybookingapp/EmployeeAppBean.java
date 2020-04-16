package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.EmployeeDTO;
import model.TEmployee;

@Stateless
@LocalBean
public class EmployeeAppBean implements EmployeeAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public EmployeeAppBean() {
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

}
