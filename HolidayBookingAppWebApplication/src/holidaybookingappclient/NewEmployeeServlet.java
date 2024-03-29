package holidaybookingappclient;

import java.io.IOException;
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

import ejbholidaybookingapp.DepartmentAppBeanRemote;
import ejbholidaybookingapp.EmployeeAppBeanRemote;
import ejbholidaybookingapp.EmployeeRoleAppBeanRemote;
import ejbholidaybookingapp.HolidayRemainingAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.DepartmentDTO;
import entityclasses.EmployeeDTO;
import entityclasses.EmployeeRoleDTO;
import entityclasses.HolidayRemainingDTO;

@WebServlet("/NewEmployeeServlet")
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private EmployeeAppBeanRemote employeeAppBean;
	@EJB
	private EmployeeRoleAppBeanRemote employeeRoleAppBean;
	@EJB
	private DepartmentAppBeanRemote departmentAppBean;
	@EJB
	private HolidayRemainingAppBeanRemote holidayRemainingAppBean;

	private static final Logger logger = Logger.getLogger(NewEmployeeServlet.class);

	public NewEmployeeServlet() {
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

			List<DepartmentDTO> departments = departmentAppBean.getDepartments();
			request.setAttribute("departments", departments);

			List<EmployeeRoleDTO> employeeRoles = employeeRoleAppBean.getRoles();
			request.setAttribute("employeeRoles", employeeRoles);

			request.getRequestDispatcher("/newemployee.jsp").forward(request, response);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String homeAddress = request.getParameter("homeAddress");
			Date hireDate = HolidayUtilsClass.formatDateFromString(request.getParameter("hireDatePicker"));
			int holDaysEntitlement = Integer.parseInt(request.getParameter("holDaysEntitlement"));
			Integer salary = Integer.parseInt(request.getParameter("salary"));
			String password = request.getParameter("password");

			int employeeRoleId = Integer.parseInt(request.getParameter("selectedEmployeeRole"));
			int departmentId = Integer.parseInt(request.getParameter("selectedEmployeeDepartment"));

			EmployeeDTO newEmp = new EmployeeDTO(0, lastName, firstName, email, phoneNumber, homeAddress, hireDate,
					holDaysEntitlement, salary, password, false, employeeRoleId, null, departmentId, null);
			employeeAppBean.addEmployee(newEmp);

			EmployeeDTO employee = employeeAppBean.getEmployeeByEmail(newEmp.getEmail());
			Date oneYear = HolidayUtilsClass.addingOneYearToHireDate(
					HolidayUtilsClass.formatDateFromString(request.getParameter("hireDatePicker")));
			Date fiveYears = HolidayUtilsClass.addingFiveYearsToHireDate(
					HolidayUtilsClass.formatDateFromString(request.getParameter("hireDatePicker")));
			HolidayRemainingDTO newHolRemaining = new HolidayRemainingDTO(0, newEmp.getHolDaysEntitlement(), oneYear, fiveYears, employee.getId());
			holidayRemainingAppBean.addHolRemainingRecord(newHolRemaining);

			response.sendRedirect("EmployeesServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
