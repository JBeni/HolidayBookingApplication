package ejbholidaybookingapp;

import java.text.ParseException;
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
import model.THolidayRequest;

@Stateless
@LocalBean
public class CheckConstraintsAppBean implements CheckConstraintsAppBeanRemote {

	@PersistenceContext
	EntityManager entityManager;

    public CheckConstraintsAppBean() {
    }

    // • No employee can exceed the number of days of holiday entitlement [COMPLETED]
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

		TEmployee employee = (TEmployee) entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.id = :id")
				.setParameter("id", holidayRequest.getEmployee().getId()).getResultList().get(0);
		// employee.getHolDaysEntitlement();
		return holidayRequestDTO;
	}

	// de refacut
	@Override
	public int getHolidayDaysRemainingById(int id) {
		THolidayRequest holidayRequest = (THolidayRequest) entityManager
				.createQuery("SELECT e FROM THolidayRequest e WHERE e.idRequest = :id")
				.setParameter("id", id).getResultList().get(0);
		return 0;
	}

	@Override
	public int getHolidayDaysEntitlementByUserId(int id) {
		TEmployee employee = (TEmployee) entityManager
				.createQuery("SELECT e FROM TEmployee e WHERE e.id = :id")
				.setParameter("id", id).getResultList().get(0);
		return employee.getHolDaysEntitlement();
	}



	// verifica daca departamentul va avea la munca 60% din dimensiunea totala a departamentului
    // • At least 60% of a department must be on duty [COMPLETED]
	//@Override
	public boolean checkDepartmentRequiredSizeGlobalConstraint(int idDep, java.util.Date holidayStart, java.util.Date holidayEnd) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :id")
				.setParameter("id", idDep).getResultList().get(0);

		@SuppressWarnings("unchecked")
		List<THolidayBooking> holidayBooking = (List<THolidayBooking>) entityManager
				.createQuery("SELECT e FROM THolidayBooking e WHERE e.department.idDep = :idDep and e.bookingBeginDate >= :holidayStart and e.bookingEndDate <= :holidayEnd")
				.setParameter("idDep", department.getIdDep()).setParameter("holidayStart", holidayStart)
				.setParameter("holidayEnd", holidayEnd).getResultList();

		int departmentSize = getDepartmentRequiredSizeGlobalConstraint(idDep);
		if (departmentSize < holidayBooking.size() + 1) {
			// request could not be made
			return false;
		}
		return true;
	}


	// REFACEREA METODEI
	@Override
	public boolean checkHolidayDaysRemaining(int userId, int bookingId) {
		THolidayRequest holidayRequest = (THolidayRequest) entityManager
				.createQuery("SELECT e FROM THolidayRequest e WHERE e.employee.id = :userId")
				.setParameter("userId", userId).getResultList().get(0);

		return false;
	}

	@Override
	public boolean checkHeadOrDeputyHeadAvailable(String roleName, String departmentName) {
		if (roleName == "Head" || roleName == "Deputy Head") {
			// verifica daca exista la munca un head ori un deputy head

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
				// nu avem nici o persoana de acel tip in vacanta
				// return true;
			}

			// dupa

			// verifica daca departamentul va avea la munca 60% din dimensiunea totala a departamentului
		}
		return false;
	}

	@Override
	public boolean checkSeniorStaffAvailable(String roleName, String departmentName) {
		if (roleName == "Manager" || roleName == "Senior member") {
			// verifica daca exista la munca cel putin un senior si un manager

			// dupa

			// verifica daca departamentul va avea la munca 60% din dimensiunea totala a departamentului
		}

		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.nameDep = :nameDep")
				.setParameter("nameDep", departmentName).getResultList().get(0);

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

		return false;
	}

	@Override
	public boolean checkManagerStaffAvailable(String roleName, String departmentName) {
		if (roleName == "Manager" || roleName == "Senior member") {
			// verifica daca exista la munca cel putin un senior si un manager

			// dupa

			// verifica daca departamentul va avea la munca 60% din dimensiunea totala a departamentului
		}

		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.nameDep = :nameDep")
				.setParameter("nameDep", departmentName).getResultList().get(0);

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

		return false;
	}



	@Override
	public int getDepartmentRequiredSizeGlobalConstraint(int idDep) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :idDep")
				.setParameter("id", idDep).getResultList().get(0);
		int requiredDepartSize = department.getIdDep() * (60 / 100);
		return requiredDepartSize;
	}

	@Override
	public int getDepartmentRequiredSizeAugustMonthConstraint(int idDep) {
		TDepartment department = (TDepartment) entityManager
				.createQuery("SELECT e FROM TDepartment e WHERE e.idDep = :idDep")
				.setParameter("id", idDep).getResultList().get(0);
		int requiredDepartSize = department.getIdDep() * (40 / 100);
		return requiredDepartSize;
	}



	@Override
	public boolean checkDepartmentRequiredSizeGlobalConstraint(int department) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean checkDepartmentRequiredSizeAugustMonthConstraint(int department) {
		// TODO Auto-generated method stub
		return false;
	}




/*
	The following constraints are applying at the time:
	    • No employee can exceed the number of days of holiday entitlement []
	    • Either the head or the deputy head of the department must be on duty [COMPLETED]
	    • At least one manager and one senior staff member must be on duty [COMPLETED]
	    • At least 60% of a department must be on duty [COMPLETED]

	In the month of August, the following constraints are applying:
	    • No employee can exceed the number of days of holiday entitlement
	    • Either the head or the deputy head of the department must be on duty
	    • At least one manager and one senior staff member must be on duty
	    • At least 40% of a department must be on duty

	1) Exceptions from those constraints between the 23rd of December to the 3rd January of every year. [COMPLETED]

	Holiday requests are manually approved by a Head of Department.

	When the system lists holiday requests, it should identify those when
	break constraints and shouldn’t allow them to be approved. Also, when
	staff apply for holiday the system should prioritise staff who have had
	a lower number of holidays in the current year, followed by those who
	have fewer days in the peak time periods, so requests should be listed
	in order of priority (highest priority first). If the approval of one
	request means that others now no longer fulfil the constraints, then
	the system would need to identify them and not allow them to be approved.
*/

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
