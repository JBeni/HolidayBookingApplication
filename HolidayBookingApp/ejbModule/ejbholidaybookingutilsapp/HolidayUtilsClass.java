package ejbholidaybookingutilsapp;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HolidayUtilsClass {

	public static Date formatDateFromString(String date) {
		try {
			java.util.Date utilStringDate = new SimpleDateFormat("dd/MM/yyyy").parse(date);
			java.sql.Date utilDate = new java.sql.Date(utilStringDate.getTime());
			return utilDate;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* https://stackoverflow.com/questions/12087419/adding-days-to-a-date-in-java
	 * Answered by swemon */
	public static Date addingOneYearToHireDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DATE, 365);
		java.util.Date newDate = c.getTime();
		return new java.sql.Date(newDate.getTime());
	}

	/* https://stackoverflow.com/questions/12087419/adding-days-to-a-date-in-java
	 * Answered by swemon */
	public static Date addingFiveYearsToHireDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int days = 5 * 365;
		c.add(Calendar.DATE, days);
		java.util.Date newDate = c.getTime();
		return new java.sql.Date(newDate.getTime());
	}

	/* Code taken from https://stackoverflow.com/questions/4600034/calculate-number-of-weekdays-between-two-dates-in-java 
	 * Answered by Shengyuan Lu **/
    public static long countWeekDaysMath(Date start, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(start);
        int w1 = c1.get(Calendar.DAY_OF_WEEK);
        c1.add(Calendar.DAY_OF_WEEK, -w1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        int w2 = c2.get(Calendar.DAY_OF_WEEK);
        c2.add(Calendar.DAY_OF_WEEK, -w2);

        long days = (c2.getTimeInMillis() - c1.getTimeInMillis()) / (1000*60*60*24);
        long daysWithoutWeekendDays = days - (days*2/7);

        if (w1 == Calendar.SUNDAY && w2 != Calendar.SATURDAY) {
            w1 = Calendar.MONDAY;
        } else if (w1 == Calendar.SATURDAY && w2 != Calendar.SUNDAY) {
            w1 = Calendar.FRIDAY;
        }

        if (w2 == Calendar.SUNDAY) {
            w2 = Calendar.MONDAY;
        } else if (w2 == Calendar.SATURDAY) {
            w2 = Calendar.FRIDAY;
        }

        return daysWithoutWeekendDays - w1 + w2 + 1;
    }

    // Calculate the number of days between two dates including saturday and sunday
    public static int countDaysMath(Date start, Date end) {
		long milliseconds = end.getTime() - start.getTime();
		int days = (int) (milliseconds / (1000 * 60 * 60 * 24) + 1);
		return days;
    }

}
