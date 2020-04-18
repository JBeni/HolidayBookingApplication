package mastering;

import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import ejbholidaybookingapp.EmployeeAppBeanRemote;
import ejbholidaybookingapp.HolidayRemainingAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.EmployeeDTO;
import entityclasses.HolidayRemainingDTO;

@Stateless
@LocalBean
public class Timer {

	@EJB
	private EmployeeAppBeanRemote employeeAppBean;

	@EJB
	private HolidayRemainingAppBeanRemote holidayRemainingAppBean;

	private static final Logger logger = Logger.getLogger(Timer.class);

	public Timer() {
	}

	/* https://stackoverflow.com/questions/16059564/ejb-3-1-timer-to-schedule-1st-of-every-month
	 * Answered by Piotr Nowicki */
	@Schedule(dayOfMonth="1", hour="23", minute="50", persistent=false)
	public void runOncePerMonthForOneYearCheckingEmployees() {
		try {
			java.util.Date currentUtilDate = new java.util.Date();
			Date currentDate = new Date(currentUtilDate.getTime());
	
			List<HolidayRemainingDTO> holRemainingRecords = holidayRemainingAppBean.getAllHolidayRemaining();
			for (HolidayRemainingDTO e : holRemainingRecords) {
				if (currentDate.compareTo(e.getOneYearDateCheck()) > 0) {
					// update the holiday remaining days to the value from the employee table
					EmployeeDTO employee = employeeAppBean.getEmployeeById(e.getIdEmp());
					HolidayRemainingDTO holRemaining = holidayRemainingAppBean.getHolidayRemainingById(e.getIdHolRemaining());
					
					holRemaining.setHolidayDaysRemaining(employee.getHolDaysEntitlement());
					holRemaining.setOneYearDateCheck(HolidayUtilsClass.addingOneYearToHireDate(e.getOneYearDateCheck()));
	
					holidayRemainingAppBean.updateHolRemainingRecord(holRemaining);
				}
			}

			System.out.println("One Year Cheched without errors...");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	/* https://stackoverflow.com/questions/16059564/ejb-3-1-timer-to-schedule-1st-of-every-month
	 * Answered by Piotr Nowicki */
	@Schedule(dayOfMonth="1", hour="0", minute="30", persistent=false)
	public void runOncePerMonthForFiveYearsCheckingEmployees() {
		try {
			java.util.Date currentUtilDate = new java.util.Date();
			Date currentDate = new Date(currentUtilDate.getTime());

			List<HolidayRemainingDTO> holRemainingRecords = holidayRemainingAppBean.getAllHolidayRemaining();
			for (HolidayRemainingDTO e : holRemainingRecords) {
				if (currentDate.compareTo(e.getFiveYearDateCheck()) > 0) {
					EmployeeDTO employee = employeeAppBean.getEmployeeById(e.getIdEmp());
					HolidayRemainingDTO holRemaining = holidayRemainingAppBean.getHolidayRemainingById(e.getIdHolRemaining());

					employee.setHolDaysEntitlement(employee.getHolDaysEntitlement() + 1);
					employeeAppBean.updateEmployee(employee);

					holRemaining.setHolidayDaysRemaining(employee.getHolDaysEntitlement());
					holRemaining.setFiveYearDateCheck(HolidayUtilsClass.addingFiveYearsToHireDate(e.getFiveYearDateCheck()));

					holidayRemainingAppBean.updateHolRemainingRecord(holRemaining);
				}
			}

			System.out.println("Five Years Cheched without errors...");
			logger.info("Beniamin");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
