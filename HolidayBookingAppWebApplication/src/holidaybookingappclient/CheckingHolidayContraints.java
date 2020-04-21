package holidaybookingappclient;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ejbholidaybookingapp.CheckConstraintsAppBeanRemote;
import ejbholidaybookingapp.EmployeeAppBeanRemote;
import ejbholidaybookingapp.HolidayRemainingAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.EmployeeDTO;
import entityclasses.HolidayRemainingDTO;

@WebServlet("/CheckingHolidayContraints")
public class CheckingHolidayContraints extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CheckConstraintsAppBeanRemote checkConstraintsAppBean;

	@EJB
	private HolidayRemainingAppBeanRemote holidayRemainingAppBean;

	@EJB
	private EmployeeAppBeanRemote employeeAppBean;

	private static final Logger logger = Logger.getLogger(CheckingHolidayContraints.class);

    public CheckingHolidayContraints() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			String isUserValid = (String) session.getAttribute("username");
			if (isUserValid == null) {
				response.sendRedirect("HolidaySystemAppServlet");
			}

			Date startDate = HolidayUtilsClass.formatDateFromString(request.getParameter("startDate"));
			Date endDate = HolidayUtilsClass.formatDateFromString(request.getParameter("endDate"));

			int userId = (int) session.getAttribute("userId");
			HolidayRemainingDTO holRemaining = holidayRemainingAppBean.getHolidayRemainingByUserId(userId);
			EmployeeDTO employee = employeeAppBean.getEmployeeById(userId);
			int holDuration = (int) HolidayUtilsClass.countWeekDaysMath(startDate, endDate);

			List<String> errorsList = checkConstraintsAppBean.checkHolidayConstraints(holRemaining.getIdHolRemaining(), holDuration,
					employee.getIdDep(), employee.getNameEmpRole(), employee.getNameDep(), startDate, endDate);

			PrintWriter out = response.getWriter();
			out.print(errorsList.toString());
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
