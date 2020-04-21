package ejbholidaybookingapp;

import java.sql.Date;
import java.text.ParseException;
import java.util.List;

import javax.ejb.Remote;
import entityclasses.HolidayRequestDTO;

@Remote
public interface CheckConstraintsAppBeanRemote {
	HolidayRequestDTO getHolidayRequestById(int id);

	int getHolidayDaysRemaining(int id);
	int getHolidayDaysEntitlementByUserId(int id);
	boolean checkAvailableHolidayDaysRemaining(int holDaysEntitlement, int holidayDuration);

	boolean checkHeadOrDeputyHeadAvailable(String roleName, String departmentName);
	boolean checkManagerStaffAvailable(String roleName, String departmentName);
	boolean checkSeniorStaffAvailable(String roleName, String departmentName);

	int getDepartmentRequiredSizeGlobalConstraint(int idDep);
	int getDepartmentRequiredSizeAugustMonthConstraint(int idDep);

	List<String> checkHolidayConstraints(Date startDate, Date endDate);

	void checkRequestForDecemberHoliday(String beginDate, String endDate) throws ParseException;
}
