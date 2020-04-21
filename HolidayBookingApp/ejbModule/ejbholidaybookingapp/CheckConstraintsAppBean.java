package ejbholidaybookingapp;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.HolidayRequestDTO;
import model.TDepartment;
import model.TEmployee;
import model.THolidayBooking;
import model.THolidayRemaining;
import model.THolidayRequest;

@Stateless
@LocalBean
public class CheckConstraintsAppBean implements CheckConstraintsAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public CheckConstraintsAppBean() {
    }

	@Override
	public HolidayRequestDTO getHolidayRequestById(int id) {
		THolidayRequest holidayRequest = (THolidayRequest) entityManager
				.createQuery("SELECT e FROM THolidayRequest e WHERE e.idRequest = :id")
				.setParameter("id", id).getResultList().get(0);
		HolidayRequestDTO holidayRequestDTO = new HolidayRequestDTO(
					holidayRequest.getIdRequest(), holidayRequest.getRequestBeginDate(), holidayRequest.getRequestEndDate(),
					holidayRequest.getHolidayDuration(),
					holidayRequest.getEmployee().getId(), holidayRequest.getPeakTime().getIdPeakTime(),
					holidayRequest.getStatus().getIdStatus(), holidayRequest.getStatus().getStatusName()
				);
		return holidayRequestDTO;
	}

	@Override
	public List<String> checkHolidayConstraints(int idHolRemaining, int holDuration, int idDep, String roleName,String departmentName,
			Date startDate, Date endDate) {
		List<String> error = new ArrayList<>();
		int holDaysRemaining = getHolidayDaysRemaining(idHolRemaining);

		if (checkAvailableHolidayDaysRemaining(holDaysRemaining, holDuration) == false) {
			error.add("Your holiday have more days than your holiday remaining days.");
		}
		if (checkHeadOrDeputyHeadAvailable(roleName, departmentName) == false) {
			error.add("A Head or a Deputy Head member of the department must be on duty.");
		}
		if (checkManagerStaffAvailable(roleName, departmentName) == false) {
			error.add("At least one manager member of the department must be on duty.");
		}
		if (checkSeniorStaffAvailable(roleName, departmentName) == false) {
			error.add("At least one senior member of the department must be on duty.");
		}
		if (checkAvailableDepartmentRequiredSizeGlobalConstraint(idDep, startDate, endDate) == false) {
			error.add("The size of the department on duty must be 60% from the total size of the department.");
		}

		return error;
	}


	// • No employee can exceed the number of days of holiday entitlement [COMPLETED]
	@Override
	public int getHolidayDaysRemaining(int id) {
		THolidayRemaining holidayRequest = (THolidayRemaining) entityManager
				.createQuery("SELECT e FROM THolidayRemaining e WHERE e.idHolRemaining = :id")
				.setParameter("id", id).getResultList().get(0);
		return holidayRequest.getHolidayDaysRemaining();
	}

	@Override
	public int getHolidayDaysEntitlementByUserId(int id) {
		TEmployee employee = (TEmployee) entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.id = :id")
				.setParameter("id", id).getResultList().get(0);
		return employee.getHolDaysEntitlement();
	}

	@Override
	public boolean checkAvailableHolidayDaysRemaining(int holDaysRemaining, int holidayDuration) {
		if (holidayDuration > holDaysRemaining) {
			// request could not be made
			return false;
		}
		return true;
	}

    // • At least 60% of a department must be on duty [COMPLETED]
	//@Override
	public boolean checkAvailableDepartmentRequiredSizeGlobalConstraint(int idDep, Date holidayStart, Date holidayEnd) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :id")
				.setParameter("id", idDep).getResultList().get(0);

		@SuppressWarnings("unchecked")
		List<THolidayBooking> holidayBooking = (List<THolidayBooking>) entityManager
				.createQuery("SELECT e FROM THolidayBooking e WHERE e.department.idDep = :id and e.bookingBeginDate >= :holidayStart and e.bookingEndDate <= :holidayEnd")
				.setParameter("id", department.getIdDep()).setParameter("holidayStart", holidayStart)
				.setParameter("holidayEnd", holidayEnd).getResultList();

		int departmentSize = getDepartmentRequiredSizeGlobalConstraint(idDep);
		if (departmentSize < holidayBooking.size() + 1) {
			// request could not be made
			return false;
		}
		return true;
	}

	@Override
	public boolean checkHeadOrDeputyHeadAvailable(String roleName, String departmentName) {
		if (roleName == "Head" || roleName == "Deputy Head") {
			TDepartment department = (TDepartment) entityManager
					.createQuery("SELECT e FROM TDepartment e WHERE e.nameDep = :nameDep")
					.setParameter("nameDep", departmentName).getResultList().get(0);

			@SuppressWarnings("unchecked")
			List<THolidayBooking> holidayBooking = (List<THolidayBooking>) entityManager
					.createQuery(
						"SELECT e FROM THolidayBooking e WHERE e.department.idDep = :idDep and (e.employee.employeeRole.nameEmpRole = :head or e.employee.employeeRole.nameEmpRole = :deputyHead)"
					).setParameter("idDep", department.getIdDep()).setParameter("head", "Head")
					.setParameter("deputyHead", "Deputy Head").getResultList();

			if (holidayBooking.size() == 0) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkSeniorStaffAvailable(String roleName, String departmentName) {
		if (roleName == "Senior member") {
			// the numbers of senior that are in holiday
			@SuppressWarnings("unchecked")
			List<TEmployee> seniorEmployeesHoliday = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.department.nameDep = :nameDep and e.employeeRole.nameEmpRole = :seniorRole")
					.setParameter("nameDep", departmentName).setParameter("seniorRole", "Senior member").getResultList();

			// get the total number of senior from a department
			@SuppressWarnings("unchecked")
			List<TEmployee> seniorEmployees = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.department.nameDep = :nameDep and e.employeeRole.nameEmpRole = :senior")
					.setParameter("nameDep", departmentName).setParameter("senior", "Senior member").getResultList();

			if (seniorEmployees.size() - seniorEmployeesHoliday.size() > 1) {
				return true;
	 		}
		}
		return false;
	}

	@Override
	public boolean checkManagerStaffAvailable(String roleName, String departmentName) {
		if (roleName == "Manager") {
			// the numbers of manager that are in holiday
			@SuppressWarnings("unchecked")
			List<TEmployee> managerEmployeesHoliday = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.department.nameDep = :nameDep and e.employeeRole.nameEmpRole = :managerRole")
					.setParameter("nameDep", departmentName).setParameter("managerRole", "Manager").getResultList();

			// get the total number of manager from a department
			@SuppressWarnings("unchecked")
			List<TEmployee> managerEmployees = (List<TEmployee>) entityManager
					.createQuery("SELECT e FROM TEmployee e WHERE e.department.nameDep = :nameDep and e.employeeRole.nameEmpRole = :manager")
					.setParameter("nameDep", departmentName).setParameter("manager", "Manager").getResultList();

			if (managerEmployees.size() - managerEmployeesHoliday.size() > 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int getDepartmentRequiredSizeGlobalConstraint(int idDep) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :id")
				.setParameter("id", idDep).getResultList().get(0);
		int requiredDepartSize = department.getIdDep() * (60 / 100);
		return requiredDepartSize;
	}


	@Override
	public int getDepartmentRequiredSizeAugustMonthConstraint(int idDep) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :id")
				.setParameter("id", idDep).getResultList().get(0);
		int requiredDepartSize = department.getIdDep() * (40 / 100);
		return requiredDepartSize;
	}

	// Constraints for December Holiday Only
	@Override
	public void checkRequestForDecemberHoliday(String beginDate, String endDate) throws ParseException {
		try {
			java.sql.Date startDate = HolidayUtilsClass.formatDateFromString(beginDate);
			java.sql.Date finalDate = HolidayUtilsClass.formatDateFromString(endDate);

			String[] startDateList = startDate.toString().split("-");
			String[] finalDateList = finalDate.toString().split("-");

			int startDay = Integer.parseInt(startDateList[2]);
			int startMonth = Integer.parseInt(startDateList[1]);
			int startYear = Integer.parseInt(startDateList[0]);

			int finalDay = Integer.parseInt(finalDateList[2]);
			int finalMonth = Integer.parseInt(finalDateList[1]);
			int finalYear = Integer.parseInt(finalDateList[0]);
			
			if (finalYear == startYear + 1) {
				if (startMonth == 12 && finalMonth == 1) {
					if (startDay >= 23 && finalDay <= 3) {
						// make the request
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
