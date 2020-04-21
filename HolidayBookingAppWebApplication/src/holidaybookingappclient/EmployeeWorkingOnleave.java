package holidaybookingappclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import ejbholidaybookingapp.BookingAppBeanRemote;
import ejbholidaybookingapp.BookingRequestAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.EmployeeDTO;

@WebServlet("/EmployeeWorkingOnleave")
public class EmployeeWorkingOnleave extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BookingRequestAppBeanRemote bookingRequestAppBeanRemote;
	@EJB
	private BookingAppBeanRemote bookingAppBeanRemote;

	private static final Logger logger = Logger.getLogger(EmployeeWorkingOnleave.class);

    public EmployeeWorkingOnleave() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			} else if (isUserValid.equals("standard-user")) {
				response.sendRedirect("BookingRequestServlet");
			}

			List<EmployeeDTO> employeesAtWork = new ArrayList<>();
			List<EmployeeDTO> employeesInHoliday = new ArrayList<>();
			String datepickerFilter = request.getParameter("datepickerFilter");
			if (datepickerFilter != null) {
				java.sql.Date dayFilter = HolidayUtilsClass.formatDateFromString(datepickerFilter);
				employeesAtWork = bookingRequestAppBeanRemote.showEmployeesAtWork(dayFilter);
				employeesInHoliday = bookingRequestAppBeanRemote.showEmployeesInHoliday(dayFilter);
			}
			request.setAttribute("employeesWorking", employeesAtWork);
			request.setAttribute("employeesHoliday", employeesInHoliday);

			request.getRequestDispatcher("/employeeWorkingOnLeave.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doGet(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
