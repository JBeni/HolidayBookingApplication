package ejbholidaybookingapp;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.DepartmentDTO;
import model.TDepartment;

@Stateless
@LocalBean
public class DepartmentAppBean implements DepartmentAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public DepartmentAppBean() {
    }

	@Override
	public DepartmentDTO getDepartmentSizeById(int id) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :id")
				.setParameter("id", id).getResultList().get(0);
		DepartmentDTO departmentDTO = new DepartmentDTO(
					department.getIdDep(), department.getNameDep(), department.getNameDepUpper(), department.getMaxNPeople()
				);
		return departmentDTO;
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

}
