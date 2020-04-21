package ejbholidaybookingapp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entityclasses.EmployeeDTO;
import entityclasses.HolidayRequestDTO;
import entityclasses.PeakTimeDTO;
import entityclasses.StatusDTO;
import model.TEmployee;
import model.TPeakTime;
import model.TStatus;
import model.THolidayRequest;
import model.THolidayBooking;

@Stateless
@LocalBean
public class BookingRequestAppBean implements BookingRequestAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public BookingRequestAppBean() {
    }

	@Override
	public boolean addHolidayRequest(HolidayRequestDTO holidayRequest) {
		try {
			TEmployee employee = entityManager.find(TEmployee.class, holidayRequest.getIdEmp());
			TPeakTime peakTime = entityManager.find(TPeakTime.class, holidayRequest.getIdPeakTime());
			TStatus status = entityManager.find(TStatus.class, holidayRequest.getIdStatus());

			THolidayRequest newHolidayRequest = new THolidayRequest(
					holidayRequest.getIdRequest(),
					holidayRequest.getRequestBeginDate(),
					holidayRequest.getRequestEndDate(),
					holidayRequest.getHolidayDuration(),
					employee,
					peakTime,
					status
			);

			entityManager.persist(newHolidayRequest);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<HolidayRequestDTO> getAllHolidayRequests() {
		@SuppressWarnings("unchecked")
		List<THolidayRequest> holidayRequests = entityManager.createNamedQuery("THolidayRequest.findAll").getResultList();
		List<HolidayRequestDTO> allHolidayRequestDTO = new ArrayList<>();
		for (THolidayRequest e : holidayRequests) {
			allHolidayRequestDTO.add(new HolidayRequestDTO(
							e.getIdRequest(), e.getRequestBeginDate(), e.getRequestEndDate(),
							e.getHolidayDuration(), e.getEmployee().getId(),
							e.getPeakTime().getIdPeakTime(), e.getStatus().getIdStatus(), e.getStatus().getStatusName()
					));
		}
		return allHolidayRequestDTO;
	}

	@Override
	public HolidayRequestDTO getHolidayRequestById(int id) {
		THolidayRequest holidayRequest = (THolidayRequest) entityManager
				.createQuery("SELECT e FROM THolidayRequest e WHERE e.idRequest = :idReq")
				.setParameter("idReq", id).getResultList().get(0);
		HolidayRequestDTO holidayRequestDTO = new HolidayRequestDTO(
				holidayRequest.getIdRequest(), holidayRequest.getRequestBeginDate(), holidayRequest.getRequestEndDate(),
				holidayRequest.getHolidayDuration(), holidayRequest.getEmployee().getId(),
				holidayRequest.getPeakTime().getIdPeakTime(), holidayRequest.getStatus().getIdStatus(), holidayRequest.getStatus().getStatusName()
			);
		return holidayRequestDTO;
	}

	@Override
	public PeakTimeDTO getTheNotPeakTime() {
		TPeakTime peakTime = (TPeakTime) entityManager
				.createQuery("SELECT e FROM TPeakTime e WHERE e.isPeakTime = 0").getResultList().get(0);
		PeakTimeDTO periodNotPeakTimeDTO = new PeakTimeDTO(
					peakTime.getIdPeakTime(), peakTime.getPeakTimeBeginDate(), peakTime.getPeakTimeEndDate(), peakTime.getIsPeakTime()
				);
		return periodNotPeakTimeDTO;
	}

	@Override
	public StatusDTO getPendingStatus() {
		TStatus pendingStatus = (TStatus) entityManager
				.createQuery("SELECT e FROM TStatus e WHERE e.statusName = 'Pending'").getResultList().get(0);
		StatusDTO pendingStatusDTO = new StatusDTO(
				pendingStatus.getIdStatus(), pendingStatus.getStatusName()
			);
		return pendingStatusDTO;
	}

	@Override
	public StatusDTO getRejectedStatus() {
		TStatus rejectedStatus = (TStatus) entityManager
				.createQuery("SELECT e FROM TStatus e WHERE e.statusName = 'Rejected'").getResultList().get(0);
		StatusDTO rejectedStatusDTO = new StatusDTO(
				rejectedStatus.getIdStatus(), rejectedStatus.getStatusName()
			);
		return rejectedStatusDTO;
	}

	@Override
	public StatusDTO getApprovedStatus() {
		TStatus approvedStatus = (TStatus) entityManager
				.createQuery("SELECT e FROM TStatus e WHERE e.statusName = 'Approved'").getResultList().get(0);
		StatusDTO approvedStatusDTO = new StatusDTO(
				approvedStatus.getIdStatus(), approvedStatus.getStatusName()
			);
		return approvedStatusDTO;
	}

	@Override
	public StatusDTO getStatusByName(String statusName) {
		TStatus status = (TStatus) entityManager
				.createQuery("SELECT e FROM TStatus e WHERE e.statusName = :name")
				.setParameter("name", statusName).getResultList().get(0);
		StatusDTO statusDTO = new StatusDTO(
				status.getIdStatus(), status.getStatusName()
			);
		return statusDTO;
	}

	@Override
	public List<HolidayRequestDTO> filterHolidayReuqestByEmployee(String lastName, String firstName, String email, String roleName, String depName) {
		@SuppressWarnings("unchecked")
		List<THolidayRequest> filterHolRequestEmp = (List<THolidayRequest>) entityManager
				.createQuery("SELECT e FROM THolidayRequest e WHERE e.employee.lastName LIKE :lastName and e.employee.firstName LIKE :firstName"
					+ " and e.employee.email LIKE :email and e.employee.employeeRole.nameEmpRole LIKE :roleName"
					+ " and e.employee.department.nameDep LIKE :depName")
				.setParameter("lastName", "%" + lastName + "%").setParameter("firstName", "%" + firstName + "%")
				.setParameter("email", "%" + email + "%").setParameter("roleName", "%" + roleName + "%")
				.setParameter("depName", "%" + depName + "%").getResultList();

		List<HolidayRequestDTO> filterHolRequestDTO = new ArrayList<>();
		for (THolidayRequest e : filterHolRequestEmp) {
			filterHolRequestDTO.add(new HolidayRequestDTO(e.getIdRequest(), e.getRequestBeginDate(), e.getRequestEndDate(),
					e.getHolidayDuration(), e.getEmployee().getId(),
					e.getPeakTime().getIdPeakTime(), e.getStatus().getIdStatus(), e.getStatus().getStatusName()));
		}
		return filterHolRequestDTO;
	}

	@Override
	public List<EmployeeDTO> showEmployeesInHoliday(Date day) {
		@SuppressWarnings("unchecked")
		List<THolidayBooking> employees = (List<THolidayBooking>) entityManager
				.createQuery("SELECT e FROM THolidayBooking e WHERE :day >= e.bookingBeginDate and :day <= e.bookingEndDate")
				.setParameter("day", day).getResultList();
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (THolidayBooking e : employees) {
			employeesDTO.add(new EmployeeDTO(e.getEmployee().getId(), e.getEmployee().getLastName(), e.getEmployee().getFirstName(),
					e.getEmployee().getEmail(), e.getEmployee().getPhoneNumber(), e.getEmployee().getHomeAddress(), e.getEmployee().getHireDate(),
					e.getEmployee().getHolDaysEntitlement(), e.getEmployee().getSalary(), e.getEmployee().getPassword(),
					e.getEmployee().getIsDeleted(), e.getEmployee().getEmployeeRole().getIdEmpRole(),
					e.getEmployee().getEmployeeRole().getNameEmpRole(), e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return employeesDTO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeDTO> showEmployeesAtWork(Date day) {
		List<EmployeeDTO> employeesInHoliday = showEmployeesInHoliday(day);
		List<Integer> employeeInHolidayDTO = new ArrayList<>();
		for (EmployeeDTO e : employeesInHoliday) {
			employeeInHolidayDTO.add(e.getId());
		}

		List<TEmployee> employeesAtWork = new ArrayList<>();
		if (employeeInHolidayDTO.size() == 0) {
			employeesAtWork = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.isDeleted != 1").getResultList();
		} else {
			employeesAtWork = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.isDeleted != 1 and e.id NOT IN :list")
					.setParameter("list", employeeInHolidayDTO).getResultList();
		}
		List<EmployeeDTO> employeesAtWorkDTO = new ArrayList<>();
		for (TEmployee e : employeesAtWork) {
			employeesAtWorkDTO.add(new EmployeeDTO(e.getId(), e.getLastName(), e.getFirstName(), e.getEmail(),
					e.getPhoneNumber(), e.getHomeAddress(), e.getHireDate(), e.getHolDaysEntitlement(), e.getSalary(),
					e.getPassword(), e.getIsDeleted(), e.getEmployeeRole().getIdEmpRole(), e.getEmployeeRole().getNameEmpRole(),
					e.getDepartment().getIdDep(), e.getDepartment().getNameDep()));
		}
		return employeesAtWorkDTO;
	}

}
