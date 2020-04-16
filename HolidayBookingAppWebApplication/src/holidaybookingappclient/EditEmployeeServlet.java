package holidaybookingappclient;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ejbholidaybookingapp.HolidaySystemAppBeanRemote;
import entityclasses.DepartmentDTO;
import entityclasses.EmployeeDTO;
import entityclasses.EmployeeRoleDTO;

@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
    private HolidaySystemAppBeanRemote holidaySystemAppBean;

	private static final Logger logger = Logger.getLogger(EditEmployeeServlet.class);

    public EditEmployeeServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession(false);
			int employeeId = Integer.parseInt(request.getParameter("id"));
			EmployeeDTO queryResult = holidaySystemAppBean.getEmployeeById(employeeId);
	
			if (queryResult != null) {
		    	request.setAttribute("employee", queryResult);
				session.setAttribute("isDeleted", queryResult.getIsDeleted());
	
				List<DepartmentDTO> departments = holidaySystemAppBean.getDepartments();
				request.setAttribute("departments", departments);
	
				List<EmployeeRoleDTO> employeeRoles = holidaySystemAppBean.getRoles();
				request.setAttribute("employeeRoles", employeeRoles);
	
		    	request.getRequestDispatcher("/editemployee.jsp").forward(request, response);
			} else {
				// error
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			EmployeeDTO queryResult = holidaySystemAppBean.getEmployeeById(id);
	
			String lastName = request.getParameter("lastName");
			String firstName = request.getParameter("firstName");
			String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String homeAddress = request.getParameter("homeAddress");
			Integer salary = Integer.parseInt(request.getParameter("salary"));
			boolean isDeleted =  Boolean.valueOf(request.getParameter("isDeleted"));
	
			int employeeRoleId = Integer.parseInt(request.getParameter("selectedEmployeeRole"));
			int departmentId = Integer.parseInt(request.getParameter("selectedEmployeeDepartment"));
	
			queryResult.setLastName(lastName);
			queryResult.setFirstName(firstName);
			queryResult.setEmail(email);
			queryResult.setPhoneNumber(phoneNumber);
			queryResult.setHomeAddress(homeAddress);
			queryResult.setSalary(salary);
			queryResult.setIsDeleted(isDeleted);
			queryResult.setIdDep(departmentId);
			queryResult.setIdEmpRole(employeeRoleId);
	
			holidaySystemAppBean.editEmployee(queryResult);
			response.sendRedirect("EmployeesServlet");
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
