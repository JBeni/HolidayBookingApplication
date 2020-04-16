package ejbholidaybookingapp;

import java.text.ParseException;
import javax.ejb.Remote;
import entityclasses.HolidayRequestDTO;

@Remote
public interface CheckConstraintsAppBeanRemote {
	HolidayRequestDTO getHolidayRequestById(int id);
	int getHolidayDaysRemainingById(int id);
	int getHolidayDaysEntitlementByUserId(int id);

	boolean checkHolidayDaysRemaining(int userId, int bookingId);
	boolean checkHeadOrDeputyHeadAvailable(String roleName, String departmentName);

	boolean checkManagerStaffAvailable(String roleName, String departmentName);
	boolean checkSeniorStaffAvailable(String roleName, String departmentName);

	int getDepartmentRequiredSizeGlobalConstraint(int idDep);
	int getDepartmentRequiredSizeAugustMonthConstraint(int idDep);

	boolean checkDepartmentRequiredSizeGlobalConstraint(int idDep);
	boolean checkDepartmentRequiredSizeAugustMonthConstraint(int idDep);
	
	void checkRequestForDecemberHoliday(String beginDate, String endDate) throws ParseException;
}
