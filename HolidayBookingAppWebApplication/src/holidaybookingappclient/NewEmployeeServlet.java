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

import org.apache.log4j.Logger;

import ejbholidaybookingapp.HolidayRemainingAppBeanRemote;
import ejbholidaybookingapp.HolidaySystemAppBeanRemote;
import ejbholidaybookingutilsapp.HolidayUtilsClass;
import entityclasses.DepartmentDTO;
import entityclasses.EmployeeDTO;
import entityclasses.EmployeeRoleDTO;
import entityclasses.HolidayRemainingDTO;

@WebServlet("/NewEmployeeServlet")
public class NewEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private HolidaySystemAppBeanRemote holidaySystemAppBean;

	@EJB
	private HolidayRemainingAppBeanRemote holidayRemainingAppBean;

	private static final Logger logger = Logger.getLogger(NewEmployeeServlet.class);

	public NewEmployeeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<DepartmentDTO> departments = holidaySystemAppBean.getDepartments();
			request.setAttribute("departments", departments);

			List<EmployeeRoleDTO> employeeRoles = holidaySystemAppBean.getRoles();
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
			holidaySystemAppBean.addNewEmployee(newEmp);

			EmployeeDTO employee = holidaySystemAppBean.getEmployeeByEmail(newEmp.getEmail());
			Date oneYear = HolidayUtilsClass.addingOneYearToHireDate(HolidayUtilsClass.formatDateFromString(request.getParameter("hireDatePicker")));
			Date fiveYears = HolidayUtilsClass.addingFiveYearsToHireDate(HolidayUtilsClass.formatDateFromString(request.getParameter("hireDatePicker")));
			HolidayRemainingDTO newHolRemaining = new HolidayRemainingDTO(0, newEmp.getHolDaysEntitlement(), oneYear, fiveYears, employee.getId());
			holidayRemainingAppBean.addHolRemainingRecord(newHolRemaining);

			response.sendRedirect("EmployeesServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
