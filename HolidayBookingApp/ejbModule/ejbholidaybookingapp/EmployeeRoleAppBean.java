package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.EmployeeRoleDTO;
import model.TEmployeeRole;

@Stateless
@LocalBean
public class EmployeeRoleAppBean implements EmployeeRoleAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public EmployeeRoleAppBean() {
    }

	@Override
	public EmployeeRoleDTO getRoleByUpperRoleName(String upperName) {
		TEmployeeRole empRole = (TEmployeeRole) entityManager
				.createQuery("SELECT e FROM TEmployeeRole e WHERE e.Dep = :name")
				.setParameter("name", upperName).getResultList().get(0);
		EmployeeRoleDTO empRoleDTO = new EmployeeRoleDTO(
				empRole.getIdEmpRole(), empRole.getNameEmpRole(), empRole.getNameEmpRoleUpper(), empRole.getCanApprove()
			);
		return empRoleDTO;
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

}
